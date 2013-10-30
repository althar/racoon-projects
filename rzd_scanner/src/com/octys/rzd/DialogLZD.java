package com.octys.rzd;

import android.content.DialogInterface;
import android.view.KeyEvent;

public class DialogLZD extends android.app.Dialog implements DialogInterface.OnCancelListener {

	private boolean menuLongPress = false;
	private TicketScannerActivity mTicketScannerActivity;
	
	public DialogLZD(TicketScannerActivity tsa) {
		this(tsa, R.style.FullscreenDialogTheme);
	}

	public DialogLZD(TicketScannerActivity tsa, int theme) {
		super(tsa, theme);
		mTicketScannerActivity = tsa;
		setOnCancelListener(this);
	}

    @Override
    public void onBackPressed() {
    }
	
	public void onCancel(DialogInterface dialog) {
	}

    public void onPrepare() {
        menuLongPress = false;
    }

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			event.startTracking();
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_BACK && menuLongPress) {
            menuLongPress = false;
			mTicketScannerActivity.maintenance();
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_FOCUS || keyCode == KeyEvent.KEYCODE_CAMERA || keyCode == KeyEvent.KEYCODE_SEARCH) {
			return true;
		}
		
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU)
			menuLongPress = false;
		return super.onKeyUp(keyCode, event);
	}

	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			menuLongPress = true;
				return true;
			}
		return super.onKeyLongPress(keyCode, event);
	}
	
}
