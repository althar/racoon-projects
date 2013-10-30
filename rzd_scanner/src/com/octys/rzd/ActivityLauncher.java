package com.octys.rzd;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ActivityLauncher extends BroadcastReceiver {
	private static final String TAG = TicketScannerActivity.class.getName();

	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			Intent newIntent = new Intent(context, TicketScannerActivity.class);
			newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(newIntent);
		} catch (Exception e) {
			Log.e(TAG, e.toString());
		}
	}

}