package com.octys.rzd;

import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CarModeOffReceiver extends BroadcastReceiver {

	public static boolean enable = true;
	@Override
	public void onReceive(Context context, Intent intent) {
		if (enable && UiModeManager.ACTION_EXIT_CAR_MODE.equals(intent.getAction())) {
			UiModeManager ui_mm = (UiModeManager)context.getSystemService(Context.UI_MODE_SERVICE);
	        ui_mm.enableCarMode(UiModeManager.ENABLE_CAR_MODE_GO_CAR_HOME);
		}
	}
}