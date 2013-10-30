package com.octys.rzd;

import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ServiceLauncher extends BroadcastReceiver {

	private static final String TAG = ServiceLauncher.class.getName();

	@Override
	public void onReceive(Context context, Intent intent) {
		if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {

			if (Consts.CAR_DOCK) {
				UiModeManager ui_mm; ui_mm = (UiModeManager)context.getSystemService(Context.UI_MODE_SERVICE);
		        ui_mm.enableCarMode(0);
			}
			
			try {
				Intent m = new Intent(context, TicketScannerActivity.class);
				m.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(m);
			} catch (Exception e){
				Log.e(TAG, "Can't start activity on boot" + e);
			}

			ComponentName comp = new ComponentName(context.getPackageName(),
					TicketScannerService.class.getName());
			ComponentName service = context.startService(new Intent()
					.setComponent(comp));
			if (null == service) {
				Log.e(TAG, "Could not start service " + comp.toString());
			}

		} else {
			Log.e(TAG, "Received unexpected intent " + intent.toString());
		}
	}
}