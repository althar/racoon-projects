package com.octys.rzd;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.octys.rzd.camera.CameraManager;

import java.io.IOException;
import java.util.Collection;

public final class ScanActivity extends Activity implements SurfaceHolder.Callback {

  private static final String TAG = ScanActivity.class.getSimpleName();
//  private static final String PACKAGE_NAME = "com.octys.rzd";
  public static final String BARCODE_DATA = "Data";
  public static final String BARCODE_FORMAT = "Format";
  public static final String REQUEST_CODE = "RequestCode";
  public static final String STATUS_TEXT_ID = "StatusTextId";

  public static final int HISTORY_REQUEST_CODE = 0x0000bacc;

  private TicketScannerApplication mApp;
  private CameraManager cameraManager;
  private ScanActivityHandler handler;
  private Result savedResultToShow;
  private ViewfinderView viewfinderView;
  private TextView statusView;
  private boolean hasSurface;
//  private IntentSource source;
  private Collection<BarcodeFormat> decodeFormats;
  private String characterSet;
//  private String versionName;
  private CountDownTimer_Scan timer_scan;
  
  private int statusTextId;

  ViewfinderView getViewfinderView() {
    return viewfinderView;
  }

  public Handler getHandler() {
    return handler;
  }

  CameraManager getCameraManager() {
    return cameraManager;
  }

  @Override
  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    
	mApp = (TicketScannerApplication)getApplication();
    statusTextId = getIntent().getIntExtra(STATUS_TEXT_ID, R.string.msg_default_status);
    
    Window window = getWindow();
    window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    setContentView(R.layout.capture);

    hasSurface = false;

    PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

    timer_scan = new CountDownTimer_Scan(this, mApp.mCfg.CFG_SCAN_TIMEOUT);
  }

  private OnClickListener listener_viewfinder = new OnClickListener() {
	@SuppressWarnings("unused")
	@Override
	public void onClick(View v) {
		if (Consts.MANUAL_AUTOFOCUS && handler!=null)
			cameraManager.requestAutoFocus(handler, R.id.auto_focus);
	}
  };
  
  @Override
  protected void onResume() {
    super.onResume();
    // CameraManager must be initialized here, not in onCreate(). This is necessary because we don't
    // want to open the camera driver and measure the screen size if we're going to show the help on
    // first launch. That led to bugs where the scanning rectangle was the wrong size and partially
    // off screen.
    cameraManager = new CameraManager(getApplication());

    viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
    viewfinderView.setCameraManager(cameraManager);
    viewfinderView.setOnClickListener(listener_viewfinder);

    statusView = (TextView) findViewById(R.id.status_view);
    statusView.setText(statusTextId);


    handler = null;

    resetStatusView();

    SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
    SurfaceHolder surfaceHolder = surfaceView.getHolder();
    if (hasSurface) {
      // The activity was paused but not stopped, so the surface still exists. Therefore
      // surfaceCreated() won't be called, so init the camera here.
      initCamera(surfaceHolder);
    } else {
      // Install the callback and wait for surfaceCreated() to init the camera.
      surfaceHolder.addCallback(this);
      surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    mApp.mBeepManager.updatePrefs();

    decodeFormats = null;
    characterSet = null;

    if (mApp.mCfg.CFG_SCAN_TIMEOUT>0) timer_scan.start();
  }
  

  @Override
  protected void onPause() {
	timer_scan.cancel();
    if (handler != null) {
      handler.quitSynchronously();
      handler = null;
    }
    cameraManager.closeDriver();
    if (!hasSurface) {
      SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
      SurfaceHolder surfaceHolder = surfaceView.getHolder();
      surfaceHolder.removeCallback(this);
    }
    
    cancel();
    super.onPause();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }
  
  public void cancel() {
	  mApp.mBeepManager.Vibrate();
      setResult(RESULT_CANCELED);
      finish();
  }
  
  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
	if (keyCode == KeyEvent.KEYCODE_MENU) {
		event.startTracking();
		return true;
	}
	
    if (keyCode == KeyEvent.KEYCODE_BACK) {
    	cancel();
        return true;
    } else if (keyCode == KeyEvent.KEYCODE_FOCUS ||
    		keyCode == KeyEvent.KEYCODE_CAMERA ||
    		keyCode == KeyEvent.KEYCODE_SEARCH) {
      // Handle these events so they don't launch the Camera app
      return true;
    }
    return super.onKeyDown(keyCode, event);
  }

  // Don't display the share menu item if the result overlay is showing.
  @Override
  public boolean onPrepareOptionsMenu(Menu menu) {
    super.onPrepareOptionsMenu(menu);
    return true;
  }

  private void decodeOrStoreSavedBitmap(Bitmap bitmap, Result result) {
    // Bitmap isn't used yet -- will be used soon
    if (handler == null) {
      savedResultToShow = result;
    } else {
      if (result != null) {
        savedResultToShow = result;
      }
      if (savedResultToShow != null) {
        Message message = Message.obtain(handler, R.id.decode_succeeded, savedResultToShow);
        handler.sendMessage(message);
      }
      savedResultToShow = null;
    }
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    if (holder == null) {
      Log.e(TAG, "*** WARNING *** surfaceCreated() gave us a null surface!");
    }
    if (!hasSurface) {
      hasSurface = true;
      initCamera(holder);
    }
  }

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {
    hasSurface = false;
  }

  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

  }

  /**
   * A valid barcode has been found, so give an indication of success and show the results.
   *
   * @param rawResult The contents of the barcode.
   * @param barcode   A greyscale bitmap of the camera data which was decoded.
   */
  public void handleDecode(Result rawResult, Bitmap barcode) {
//	mApp.mBeepManager.playBeepSoundAndVibrate();
	drawResultPoints(barcode, rawResult);

	statusView.setVisibility(View.GONE);
	viewfinderView.setVisibility(View.GONE);

	resetStatusView();
	Intent data = new Intent();
	data.putExtra(BARCODE_DATA, rawResult.getText());
	data.putExtra(BARCODE_FORMAT, rawResult.getBarcodeFormat().toString());
	setResult(Activity.RESULT_OK, data);
	finish();
    
    
  }

  /**
   * Superimpose a line for 1D or dots for 2D to highlight the key features of the barcode.
   *
   * @param barcode   A bitmap of the captured image.
   * @param rawResult The decoded results which contains the points to draw.
   */
  private void drawResultPoints(Bitmap barcode, Result rawResult) {
    ResultPoint[] points = rawResult.getResultPoints();
    if (points != null && points.length > 0) {
      Canvas canvas = new Canvas(barcode);
      Paint paint = new Paint();
      paint.setColor(getResources().getColor(R.color.result_image_border));
      paint.setStrokeWidth(3.0f);
      paint.setStyle(Paint.Style.STROKE);
      Rect border = new Rect(2, 2, barcode.getWidth() - 2, barcode.getHeight() - 2);
      canvas.drawRect(border, paint);

      paint.setColor(getResources().getColor(R.color.result_points));
      if (points.length == 2) {
        paint.setStrokeWidth(4.0f);
        drawLine(canvas, paint, points[0], points[1]);
      } else if (points.length == 4 &&
                 (rawResult.getBarcodeFormat() == BarcodeFormat.UPC_A ||
                  rawResult.getBarcodeFormat() == BarcodeFormat.EAN_13)) {
        // Hacky special case -- draw two lines, for the barcode and metadata
        drawLine(canvas, paint, points[0], points[1]);
        drawLine(canvas, paint, points[2], points[3]);
      } else {
        paint.setStrokeWidth(10.0f);
        for (ResultPoint point : points) {
          canvas.drawPoint(point.getX(), point.getY(), paint);
        }
      }
    }
  }

  private static void drawLine(Canvas canvas, Paint paint, ResultPoint a, ResultPoint b) {
    canvas.drawLine(a.getX(), a.getY(), b.getX(), b.getY(), paint);
  }

/*
  private void sendReplyMessage(int id, Object arg) {
    Message message = Message.obtain(handler, id, arg);
    long resultDurationMS = getIntent().getLongExtra(Intents.Scan.RESULT_DISPLAY_DURATION_MS,
                                                     DEFAULT_INTENT_RESULT_DURATION_MS);
    if (resultDurationMS > 0L) {
      handler.sendMessageDelayed(message, resultDurationMS);
    } else {
      handler.sendMessage(message);
    }
  }
*/
  
  

  private void initCamera(SurfaceHolder surfaceHolder) {
    try {
      cameraManager.openDriver(surfaceHolder);
      // Creating the handler starts the preview, which can also throw a RuntimeException.
      if (handler == null) {
        handler = new ScanActivityHandler(this, decodeFormats, characterSet, cameraManager);
      }
      decodeOrStoreSavedBitmap(null, null);
    } catch (IOException ioe) {
      Log.w(TAG, ioe);
      displayFrameworkBugMessageAndExit();
    } catch (RuntimeException e) {
      // Barcode Scanner has seen crashes in the wild of this variety:
      // java.?lang.?RuntimeException: Fail to connect to camera service
      Log.w(TAG, "Unexpected error initializing camera", e);
      displayFrameworkBugMessageAndExit();
    }
  }

  private void displayFrameworkBugMessageAndExit() {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle(getString(R.string.app_name));
    builder.setMessage(getString(R.string.msg_camera_framework_bug));
    builder.setPositiveButton(R.string.button_ok, new FinishListener(this));
    builder.setOnCancelListener(new FinishListener(this));
    builder.show();
  }

  public void restartPreviewAfterDelay(long delayMS) {
    if (handler != null) {
      handler.sendEmptyMessageDelayed(R.id.restart_preview, delayMS);
    }
    resetStatusView();
  }

  private void resetStatusView() {
    statusView.setVisibility(View.VISIBLE);
    viewfinderView.setVisibility(View.VISIBLE);
  }

  public void drawViewfinder() {
    viewfinderView.drawViewfinder();
  }
  
}

class CountDownTimer_Scan extends CountDownTimer {
	private ScanActivity activity;
	public CountDownTimer_Scan(ScanActivity activity, int timeout) {
		super(timeout*1000L, 1000L);
		this.activity = activity;
	}

	@Override
	public void onFinish() {
		activity.cancel();
	}

	@Override
	public void onTick(long millisUntilFinished) {
	}
}
