package com.octys.rzd;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Calendar;

public class TicketScannerService extends Service {
	private static final String TAG = TicketScannerService.class.getName();

	private String PREFS_CONFIG_CHECK_TIME = "config_check_time";
	private TicketScannerApplication mApp;
	private long config_check_time = -1;

/*	private static volatile PowerManager.WakeLock lockStatic = null;

	synchronized private static PowerManager.WakeLock getLock(Context context) {
		if (lockStatic == null) {
			PowerManager mgr = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
			lockStatic = mgr.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, TAG);
			lockStatic.setReferenceCounted(true);
		}
		return (lockStatic);
	}
*/
	
	public class TicketScannerBinder extends Binder {
		TicketScannerService getService() {
			return TicketScannerService.this;
		}
	}

	private final IBinder mBinder = new TicketScannerBinder();

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		mApp = (TicketScannerApplication) getApplication();
		mApp.launchActivity();
		
		Log.w(TAG, "start ticket scanner service, version name "+mApp.strVersionName+" version code "+mApp.strVersionCode);

        config_check_time = mApp.mPrefs.getLong(PREFS_CONFIG_CHECK_TIME, -1);

        Thread thr = new Thread(null, mTask, "TicketScannerService");
        thr.start();

        
//		IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
//		registerReceiver(mNetworkStateReceiver, filter);
        
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// Log.i("TicketScannerService", "Received start id " + startId + ": " +
		// intent);
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		Log.w(TAG, "Service onDdestroy");
    	SharedPreferences.Editor editor = mApp.mPrefs.edit();
    	editor.putLong(PREFS_CONFIG_CHECK_TIME, config_check_time);
    	editor.commit();
    	mApp.onDestroy();
	}
	
    Runnable mTask = new Runnable() {
    	
    	void wait(int minutes) {
            long endTime = System.currentTimeMillis() + minutes*60*1000;
            while (System.currentTimeMillis() < endTime) {
                synchronized (mBinder) {
                    try {
                        mBinder.wait(endTime - System.currentTimeMillis());
                    } catch (Exception e) {
                    }
                }
            }
    	}
  	
    	void get_config() {
    		long now=Calendar.getInstance().getTimeInMillis();
    		if ( config_check_time>0 &&
    			(now-config_check_time) < mApp.mCfg.CFG_CONF_REFRESH*1000L*60L) return;
    		try {
    			String newCfgString = mApp.mCommCfg.getString(Consts.CFG_CONFIG_SERVER_ADDRESS);
//    			Log.w(TAG, "new config:"+mApp.NL+cfg);
    			if (newCfgString.length()>0) {
    				config_check_time=now;    	        	
    				mApp.pushNewCfg(newCfgString);
    			} else
    				Log.w(TAG, "Confituration not loaded");
    		} catch (Exception e) {
    			Log.w(TAG, "Configuration loading error: "+e.toString());
    		}
    	}
    	
		public void run() {
			Log.w(TAG, "Thread started. cfg version=" + mApp.mCfg.CFG_LAST_UPDATE_VERSION + "  version=" + mApp.strVersionName);
			mApp.showNotification();
			mApp.wait_IMEI();
			mApp.showNotification();
			if (mApp.mComm.isInternetOn()) {
				get_config();
				mApp.updateCfg();
				mApp.checkUpdates();
			}
//			PowerManager.WakeLock lock = getLock(getApplicationContext());
			while (true) {
				mApp.flushLog();
				// Log.w(TAG,"service main loop, counter="+counter+" internet="+mApp.mComm.isInternetOn());
				if (mApp.mComm.isInternetOn()) {
					get_config();
					mApp.checkUpdates();
					//if (!lock.isHeld()) lock.acquire();
					mApp.sendData();
					mApp.showNotification();
				}
				//if (lock.isHeld()) lock.release();
				
				wait(mApp.mCfg.CFG_SETTINGS_DB_LOOKUP_INTERVAL);
			}
			// TicketScannerService.this.stopSelf();
		}
	};	
     
}
