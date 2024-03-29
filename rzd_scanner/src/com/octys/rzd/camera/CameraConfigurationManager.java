/*
 * Copyright (C) 2010 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.octys.rzd.camera;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Camera;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.octys.rzd.Consts;

import java.util.Collection;

/**
 * A class which deals with reading, parsing, and setting the camera parameters which are used to
 * configure the camera hardware.
 */
final class CameraConfigurationManager {

  private static final String TAG = "CameraConfiguration";
//  private static final int MIN_PREVIEW_PIXELS = 320 * 240; // small screen
  private static final int MIN_PREVIEW_PIXELS = 640 * 480; // small screen
  private static final int MAX_PREVIEW_PIXELS = 800 * 480; // large/HD screen

  private final Context context;
  private Point screenResolution;
  private Point cameraResolution;
  private String flashModeOn="";
  private String flashModeOff = "";
  private boolean useLED;

  CameraConfigurationManager(Context context) {
    this.context = context;
  }

  /**
   * Reads, one time, values from the camera that are needed by the app.
   */
  void initFromCameraParameters(Camera camera) {
    Camera.Parameters parameters = camera.getParameters();
    WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    Display display = manager.getDefaultDisplay();
    int width = display.getWidth();
    int height = display.getHeight();
    // We're landscape-only, and have apparently seen issues with display thinking it's portrait 
    // when waking from sleep. If it's not landscape, assume it's mistaken and reverse them:
    if (width < height) {
      Log.i(TAG, "Display reports portrait orientation; assuming this is incorrect");
      int temp = width;
      width = height;
      height = temp;
    }
    screenResolution = new Point(width, height);
//    Log.i(TAG, "Screen resolution: " + screenResolution);
    cameraResolution = findBestPreviewSizeValue(parameters, screenResolution, false);
//    Log.i(TAG, "Camera resolution: " + cameraResolution);
    
	flashModeOff = findSettableValue(parameters.getSupportedFlashModes(),
            Camera.Parameters.FLASH_MODE_OFF);
    flashModeOn = findSettableValue(parameters.getSupportedFlashModes(),
            Camera.Parameters.FLASH_MODE_TORCH,
            Camera.Parameters.FLASH_MODE_ON);
    useLED = flashModeOn!=null;
  }

  void setDesiredCameraParameters(Camera camera) {
    Camera.Parameters parameters = camera.getParameters();

    if (parameters == null) {
      Log.w(TAG, "Device error: no camera parameters are available. Proceeding without configuration.");
      return;
    }

    String focusMode = findSettableValue(parameters.getSupportedFocusModes(),
    	Camera.Parameters.FOCUS_MODE_AUTO,
        Camera.Parameters.FOCUS_MODE_MACRO);
    if (focusMode != null) {
      parameters.setFocusMode(focusMode);
    }

    parameters.setPreviewSize(cameraResolution.x, cameraResolution.y);
        
    if (parameters.isZoomSupported() && parameters.getZoomRatios().indexOf(Consts.ZOOM)>-1)
    	parameters.setZoom(Consts.ZOOM);

    if (parameters.getSupportedSceneModes().indexOf(Camera.Parameters.SCENE_MODE_BARCODE)>-1)
    	parameters.setSceneMode(Camera.Parameters.SCENE_MODE_BARCODE);
/*    
    List<int[]>supportedFpsRange = parameters.getSupportedPreviewFpsRange();
    if (supportedFpsRange!=null && !supportedFpsRange.isEmpty()) {
    	int maxFps = 0;
    	int minFps = 0;
        int i=1;
    	for (int [] r : supportedFpsRange) {
    		if (maxFps < r[Camera.Parameters.PREVIEW_FPS_MAX_INDEX]) {
    				minFps = r[Camera.Parameters.PREVIEW_FPS_MIN_INDEX];
    				maxFps = r[Camera.Parameters.PREVIEW_FPS_MAX_INDEX];
    			}
    		Log.w(TAG, "FPS range "+i+" "+r[Camera.Parameters.PREVIEW_FPS_MIN_INDEX]+" - "+r[Camera.Parameters.PREVIEW_FPS_MAX_INDEX] );
    		i++;
    	}
    	if (maxFps>0)
    		parameters.setPreviewFpsRange(minFps, maxFps);
    }
*/    
    camera.setParameters(parameters);
  }

  Point getCameraResolution() {
    return cameraResolution;
  }

  Point getScreenResolution() {
    return screenResolution;
  }

  private static Point getMaxPreviewSize(Camera.Parameters aParameters) {
	  Point p = new Point(0,0);
	  for (Camera.Size s : aParameters.getSupportedPreviewSizes ()) {
		  if (s.width > p.x) {
			  p.x=s.width;
			  p.y=s.height;
		  }
	  }
	  return p;
  }
 
  private static Point findBestPreviewSizeValue(Camera.Parameters parameters,
                                                Point screenResolution,
                                                boolean portrait) {
    Point bestSize = null;
    int diff = Integer.MAX_VALUE;
    for (Camera.Size supportedPreviewSize : parameters.getSupportedPreviewSizes()) {
      int pixels = supportedPreviewSize.height * supportedPreviewSize.width;
      if (pixels < MIN_PREVIEW_PIXELS || pixels > MAX_PREVIEW_PIXELS) {
        continue;
      }
      int supportedWidth = portrait ? supportedPreviewSize.height : supportedPreviewSize.width;
      int supportedHeight = portrait ? supportedPreviewSize.width : supportedPreviewSize.height;
      int newDiff = Math.abs(screenResolution.x * supportedHeight - supportedWidth * screenResolution.y);
      if (newDiff == 0) {
        bestSize = new Point(supportedWidth, supportedHeight);
        break;
      }
      if (newDiff < diff) {
        bestSize = new Point(supportedWidth, supportedHeight);
        diff = newDiff;
      }
    }
    if (bestSize == null) {
      Camera.Size defaultSize = parameters.getPreviewSize();
      bestSize = new Point(defaultSize.width, defaultSize.height);
    }
    
    if (Consts.USE_MAX_PREVIEW_SIZE)
    	bestSize = getMaxPreviewSize(parameters);
//    Log.d(TAG, "Maximum camera resolution = "+bestSize);
    
    return bestSize;
  }

  private static String findSettableValue(Collection<String> supportedValues,
                                          String... desiredValues) {
    Log.i(TAG, "Supported values: " + supportedValues);
    String result = null;
    if (supportedValues != null) {
      for (String desiredValue : desiredValues) {
        if (supportedValues.contains(desiredValue)) {
          result = desiredValue;
          break;
        }
      }
    }
//    Log.i(TAG, "Settable value: " + result);
    return result;
  }

  void LED(Camera camera, boolean state) {
	if (camera==null || !useLED) return;
    Camera.Parameters parameters = camera.getParameters();
    parameters.setFlashMode(state ? flashModeOn : flashModeOff);
    camera.setParameters(parameters);
  }

}
