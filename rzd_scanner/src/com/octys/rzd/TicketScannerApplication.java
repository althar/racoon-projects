package com.octys.rzd;

import android.app.*;
import android.content.*;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;
import com.octys.rzd.BeepManager;

import java.util.Map;

//                 <category android:name="android.intent.category.CAR_DOCK" />

public class TicketScannerApplication extends Application {
	private static final String TAG = TicketScannerApplication.class.getName();

	public static final String SESSION_TIME = "session_time";

	private int NOTIFICATION_ID = 1;

	private String mIMEI = null;
	// private String mIMEI_hash;
	private String mFingerprint;

	private SDCardLogger sdcard_log;
	private DatabaseTools mDBTools;
	private UpdateTools mUpdateTools;
	public Cfg mCfg = new Cfg();
	private String mNewConfigString = null;
	public Communicator mComm, mCommCfg;
	private NotificationManager mNM;
	public SharedPreferences mPrefs;
	public String NL;
	public BeepManager mBeepManager;
	public String strVersionName;
	public String strVersionCode;

	private String personalIdString = null;
	private long last_scan_time = 0L;
	private int ticket_count = 0;

	private TicketScannerActivity activity = null;

	private static final String CFG_JSON_STRING = "cfg_json_string";
	private static TicketScannerApplication _app;

	static TicketScannerApplication getApp() {
		return _app;
	}

	@Override
	public void onCreate() {
		_app = this;
		UiModeManager ui_mm = (UiModeManager) getSystemService(Context.UI_MODE_SERVICE);
		if (Consts.CAR_DOCK)
			ui_mm.enableCarMode(0);
		else
			ui_mm.disableCarMode(0);

		sdcard_log = new SDCardLogger(Consts.SDCARD_LOG_DIR, Consts.SDCARD_LOG_MAX);
		mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		mDBTools = DatabaseTools.getDatabaseTools(this);
		mUpdateTools = new UpdateTools(this);

		IntentFilter filter;

		filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
		registerReceiver(mScreenOffReceiver, filter);

		mFingerprint = Consts.isDeviceSupported() ? null : Build.FINGERPRINT;

		mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		NL = System.getProperty("line.separator");

		try {
			mCfg = new Cfg(mPrefs.getString(CFG_JSON_STRING, null));
		} catch (Exception e) {
			mCfg = new Cfg();
		}

		mComm = new Communicator(this, mCfg.CFG_PRODUCTION_SERVER_USER, mCfg.CFG_PRODUCTION_SERVER_PASSWORD);
		mCommCfg = new Communicator(this, Consts.CFG_CONFIG_SERVER_LOGIN, Consts.CFG_CONFIG_SERVER_PASSWORD);
		mBeepManager = new BeepManager(this);

		try {
			PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);

			strVersionCode = "Version Code: " + String.valueOf(packageInfo.versionCode);

			strVersionName = packageInfo.versionName;

		} catch (NameNotFoundException e) {
			Log.w(TAG, e);
			strVersionName = "Cannot load Version!";
			strVersionCode = "1";
		}
	}

	public void onDestroy() { // called from Shutdown broadcast receiver
		if (activity != null)
			activity.finish();
		mUpdateTools.onDestroy();
		mDBTools.close();
		mNM.cancel(NOTIFICATION_ID);
	}

	public void exit() {
		CarModeOffReceiver.enable = false;
		UiModeManager ui_mm = (UiModeManager) getSystemService(Context.UI_MODE_SERVICE);
		ui_mm.disableCarMode(0);
		onDestroy();
	}

	BroadcastReceiver mScreenOffReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
				// Log.w(TAG, "screen off");
				restart_session_timer();
			}
		}
	};

	public void setActivity(TicketScannerActivity a) {
		activity = a;
	}

	public void showNotification() {
		int counter = 0;
		String text;
		Notification notification = new Notification();
		notification.icon = R.drawable.icon24;
		notification.flags = Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR;
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, TicketScannerActivity.class), 0);

		counter = mDBTools.getCount();
		if (mIMEI == null) {
			text = getString(R.string.status_starting);
		} else {
			text = getString(mComm.isInternetOn() ? R.string.status_network_on : R.string.status_network_off);
			text += getString(counter > 0 ? R.string.status_data_waiting : R.string.status_no_data);
		}
		notification.setLatestEventInfo(this, getString(R.string.app_name), text, contentIntent);
		notification.number = counter;
		mNM.notify(NOTIFICATION_ID, notification);

	}

	public void wait_IMEI() {
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		while (mIMEI == null) {
			if ((mIMEI = telephonyManager.getDeviceId()) != null) {
				sdcard_log("IM {0}", mIMEI);
				return;
			}
			try {
				wait(1000);
			} catch (InterruptedException E) {
			}
		}
	}

	void sendData() {
		DatabaseHelper.RecordIterator ri = mDBTools.getAll();
		for (DatabaseHelper.Record r : ri) {
			if (r.operator_id == null || r.operator_id.length() == 0)
				r.operator_id = Consts.DEFAULT_OPERATOR_ID;
			if (mComm.send(r, mIMEI/* mIMEI_hash */, mFingerprint)) {
				mDBTools.delData(r.id);
				showNotification();
			} else {
				if (!mComm.isInternetOn())
					break;
			}
		}
		ri.close();
	}

	public void putData(String rr, String lt, boolean incrementCounter) {
		if (personalIdString != null) {
			mDBTools.putData(rr, lt, personalIdString);
			showNotification();
			if (incrementCounter)
				ticket_count++;
		} else
			Log.e(TAG, "Empty personal id. rr=" + rr + " lt=" + lt);
	}

	public void sdcard_log(String message, Object... parameters) {
		sdcard_log.log(message, parameters);
	}

	public String getPersonalIdString() {
		return personalIdString;
	}

	public int getTicketCount() {
		return ticket_count;
	}

	public void start_session(String id) {
		// Log.w(TAG, "session started.");
		personalIdString = id;
		restart_session_timer();
		ticket_count = 0;
	}

	public void terminate_session() {
		// Log.w(TAG, "session terminated.");
		personalIdString = null;
	}

	public void restart_session_timer() {
		last_scan_time = SystemClock.elapsedRealtime();
	}

	public boolean is_session_expired() {
		if (last_scan_time == 0 || personalIdString == null)
			return true;
		long now = SystemClock.elapsedRealtime();
		long time_from_last_scan = now - last_scan_time;
		// Log.w(TAG,
		// "check_session_timeout: last_scan_time="+last_scan_time+"; now="+now+"; diff="+time_from_last_scan);
		// Log.w(TAG,
		// "check_session_timeout: configured timeout (minutes)="+mCfg.CFG_SETTINGS_SESSION_TIMEOUT);
		return time_from_last_scan > mCfg.CFG_SETTINGS_SESSION_TIMEOUT * 60 * 1000L;
	}

	public void updateCfg(String new_config_string) {
		try {
			Cfg new_config = new Cfg(new_config_string);
			if (new_config != null) {

				String curr_config_string = mPrefs.getString(CFG_JSON_STRING, null);
				if (curr_config_string == null || !new_config_string.equals(curr_config_string)) {
					mPrefs.edit().putString(CFG_JSON_STRING, new_config_string).commit();
					Log.w(TAG, "Config updated.");
					// mUpdateTools.restart(Consts.RESTART_DELAY_RECONFIG);
				} else
					Log.w(TAG, "Config not changed.");
			}
		} catch (Exception e) {
			Log.w(TAG, "Configuration error:" + e.toString() + " " + new_config_string);
		}
	}

	public void toast(String msg) {
		if (msg != null && msg.length() > 0) {
			Toast t = Toast.makeText(this, msg, Toast.LENGTH_LONG);
			t.setGravity(Gravity.CENTER, 0, 0);
			t.show();
		}
	}

	public void toast(int id) {
		toast(getString(id));
	}

	public void pushNewCfg(String newCfgString) {
		mNewConfigString = newCfgString;
	}

	public void updateCfg() {
		try {
			if (mNewConfigString == null)
				return;
			Cfg new_config = new Cfg(mNewConfigString);
			String curr_config_string = mPrefs.getString(CFG_JSON_STRING, null);
			if (curr_config_string == null || !mNewConfigString.equals(curr_config_string)) {
				mPrefs.edit().putString(CFG_JSON_STRING, mNewConfigString).commit();
				mCfg = new_config;
				Log.w(TAG, "Config updated.");
			} // else Log.w(TAG, "Config remain unchanged.");

		} catch (Exception e) {
			Log.w(TAG, "Configuration error:" + e.toString() + " " + mNewConfigString);
		}
		mNewConfigString = null;
	}

	public void checkUpdates() {
		mUpdateTools.check();
	}

	public boolean manageUpdates() {
		updateCfg();
		return mUpdateTools.manageUpdates();
	}

	public void flushLog() {
		sdcard_log.flush();
	}

	public boolean isNonMarketAppsAllowed() {
		// Log.w(TAG,
		// "isNonMarketAppsAllowed()="+Settings.Secure.getInt(getContentResolver(),
		// Settings.Secure.INSTALL_NON_MARKET_APPS, 0));
		return Settings.Secure.getInt(getContentResolver(), Settings.Secure.INSTALL_NON_MARKET_APPS, 0) > 0;
	}

	public boolean isDebuggingEnabled() {
		int adb = Settings.Secure.getInt(getContentResolver(), Settings.Secure.ADB_ENABLED, 0);
		// Log.w(TAG, "isDebuggingEnabled()="+adb);
		return adb != 0;
	}

	public void relaunch() {
		mUpdateTools.scheduleActivity(500);
	}

	public static void stackTrace() {
		Map<Thread, StackTraceElement[]> m = Thread.getAllStackTraces();
		for (Map.Entry<Thread, StackTraceElement[]> e : m.entrySet()) {
			Log.w(TAG, e.getKey().toString());
			for (StackTraceElement s : e.getValue()) {
				Log.w(TAG, "  " + s);
			}
		}
	}

	public void launchActivity() {
		try {
			Intent m = new Intent(this, TicketScannerActivity.class);
			m.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(m);
		} catch (Exception e) {
			Log.e(TAG, "Can't start activity on boot" + e);
		}
	}
}
