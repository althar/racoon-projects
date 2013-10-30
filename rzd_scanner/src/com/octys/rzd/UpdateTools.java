package com.octys.rzd;

import android.app.AlarmManager;
import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Process;
import android.util.Base64;
import android.util.Log;

import java.io.File;
import java.net.URI;

public class UpdateTools {
	private static final String TAG = UpdateTools.class.getName();

	private TicketScannerApplication mApp;
	private boolean cleanNecessay = true;
	private String downloadedFileName = null;
	private DownloadManager dl_mgr = null;
	private long downloadId = -1L;

	private static final String download_upgrade_fname = "ticketscanner.apk";
	private File download_upgrade_dir;

	public UpdateTools(TicketScannerApplication app) {
		mApp = app;
		mApp.registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
		download_upgrade_dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
		download_upgrade_dir.mkdirs();

		dl_mgr = (DownloadManager) mApp.getSystemService(Context.DOWNLOAD_SERVICE);
	}

	public void onDestroy() {
		mApp.unregisterReceiver(onComplete);
	}

	public boolean updateNecessary() {
		return mApp.mCfg.CFG_LAST_UPDATE_VERSION != null
				&& !mApp.strVersionName.equalsIgnoreCase(mApp.mCfg.CFG_LAST_UPDATE_VERSION);
	}
	
	public void check() {
		if (cleanNecessay || updateNecessary()) {
			clean();
			cleanNecessay = false;
		}

		if (updateNecessary())
			startDownload();
	}

	public void clean() {
		Log.w(TAG, "clean");
		try {
			File file = new File(download_upgrade_dir, download_upgrade_fname);
			if (!file.exists()) return;
		
			String apk = file.getAbsolutePath();
			Log.w("TAG", "file exists " + apk);
			
			String apkVersionName = getVersionName(apk);
			Log.w("TAG", "file version " + apkVersionName+"; cfg version "+mApp.mCfg.CFG_LAST_UPDATE_VERSION+"; this version "+mApp.strVersionName);

			if (apkVersionName == null || !apkVersionName.equalsIgnoreCase(mApp.mCfg.CFG_LAST_UPDATE_VERSION)) {
				Log.w(TAG, "deleting file "+apk+" version="+apkVersionName);
				file.delete();
				downloadedFileName = null;
			} else {
				Log.w(TAG, "Nothing to delete, file contains required update: "+apk);
				downloadedFileName = apk;
			}
		} catch (Exception e) {
			Log.w("TAG", "Clean old file error: " + e);
		}
	}

	boolean empty(String s) {
		return s==null || s.length()==0;
	}
	
	public void startDownload() {
		if (downloadId != -1L || downloadedFileName!=null)
			return;

		if (empty(mApp.mCfg.CFG_LAST_UPDATE_PATH) ||
			empty(mApp.mCfg.CFG_PRODUCTION_SERVER_USER) || 
			empty(mApp.mCfg.CFG_PRODUCTION_SERVER_PASSWORD)	) {
			Log.w(TAG, "Not configured for update. Check update path, login, password.");
			return;
		}
		
		Log.w(TAG, "Start download update");

		Uri uri = Uri.parse(mApp.mCfg.CFG_LAST_UPDATE_PATH);
		if (uri == null) {
			Log.w(TAG, "update uri parse error. uri=" + mApp.mCfg.CFG_LAST_UPDATE_PATH);
			return;
		}

		try {
			String authentication = mApp.mCfg.CFG_PRODUCTION_SERVER_USER + ":" + mApp.mCfg.CFG_PRODUCTION_SERVER_PASSWORD;
			String auth = "Basic " + Base64.encodeToString(authentication.getBytes(), 0);
			downloadId = dl_mgr.enqueue(new DownloadManager.Request(uri).addRequestHeader("Authorization", auth)
					.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
					.setVisibleInDownloadsUi(false).setAllowedOverRoaming(Consts.ALLOW_DOWNLOAD_IN_ROAMING)
					.setTitle(mApp.getString(R.string.downloader_title)).setDescription(mApp.getString(R.string.downloader_description))
					.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, download_upgrade_fname));
		} catch (Exception e) {
			Log.e(TAG, "Start download error: " + e);
		}
	}

	BroadcastReceiver onComplete = new BroadcastReceiver() {
		public void onReceive(Context ctxt, Intent intent) {
			Log.w(TAG, "Download complete");
			String action = intent.getAction();
			if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)
					&& downloadId == intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0)) {
				long id = downloadId;
				downloadId = -1;
				try {
					Query query = new Query();
					query.setFilterById(id);
					Cursor c = dl_mgr.query(query);
					if (c.moveToFirst()) {
						int result = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
						int reason = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_REASON));
						if (DownloadManager.STATUS_SUCCESSFUL == result) {
							Log.w(TAG, "Download succeeded");
							String downloadedFileUriString = c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
							File f = new File(new URI(downloadedFileUriString));
							downloadedFileName = f.getAbsolutePath();
						} else
							Log.w(TAG, "Download failed: " + reason);
					}
				} catch (Exception e) {
					Log.w(TAG, "open error " + e.toString());
				}
			}
		}
	};

	
	
	@SuppressWarnings("unused")
	public boolean manageUpdates() {
		Log.w(TAG, "manageUpdates: mApp.mCfg.CFG_LAST_UPDATE_VERSION="+mApp.mCfg.CFG_LAST_UPDATE_VERSION+"; downloadedFileUriString="+downloadedFileName);
		if (!updateNecessary()) return false;
		if (downloadedFileName==null) return false;
		try {
			Log.w(TAG, "manageUpdates, file=" + downloadedFileName);

			File f = new File(downloadedFileName);
			if (!f.exists()) {
				Log.w(TAG, "File not found");
				downloadedFileName = null;
				return false;
			}
			
			String versionName = getVersionName(downloadedFileName);
			if (!mApp.mCfg.CFG_LAST_UPDATE_VERSION.equalsIgnoreCase(versionName)) {
				Log.w(TAG, "File versions missmatch: downloaded file version is "+versionName+"; required version "+mApp.mCfg.CFG_LAST_UPDATE_VERSION);
				return false;
			}

//			String md5 = MD5.get(f);
//			if (md5 == null || !md5.equalsIgnoreCase(mApp.mCfg.CFG_LAST_UPDATE_MD5)) {
//				Log.e(TAG, "Incorrect MD5 of downloaded file: \n" + md5 + " <-- calculated\n" + mApp.mCfg.CFG_LAST_UPDATE_MD5
//						+ " <-- expected");
//				return false;
//			}

			Uri uri = Uri.fromFile(f);
			installPackage(uri);
			if (Consts.RESTART_DELAY_UPGRADE>0)
				scheduleActivity(Consts.RESTART_DELAY_UPGRADE);
			return true;
		} catch (Exception e) {
            System.out.println(e.toString());
			Log.w(TAG, e);
			return false;
		}
	}
	
	public void installPackage(Uri uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        mApp.startActivity(intent);
//		Intent i = new Intent(Intent.ACTION_VIEW, uri).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).setDataAndType(uri,
//				"application/vnd.android.package-archive");
//		mApp.startActivity(i);
	}

	public String getVersionName(String apk) {
		PackageManager pm = mApp.getPackageManager();
		PackageInfo pi = pm.getPackageArchiveInfo(apk, 0);
		if (pi == null)
			return null;
		return pi.versionName;
	}

	public void scheduleActivity(int delay) {
		AlarmManager a = (AlarmManager) mApp.getSystemService(Context.ALARM_SERVICE);
		long now = System.currentTimeMillis();
		/*
		 * PendingIntent activity = PendingIntent.getActivity(this, 0, new
		 * Intent(this, this.getClass()), 0); a.set(AlarmManager.RTC,
		 * now+Consts.RESTART_DELAY_MILLIS, activity);
		 */

		PendingIntent pi = PendingIntent.getActivity(mApp, 0,
				new Intent(mApp, TicketScannerActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION), 0);
		a.set(AlarmManager.RTC, now + delay, pi);
	}

	public void restart(int delay) {
		scheduleActivity(delay);
		Process.sendSignal(Process.myPid(), Process.SIGNAL_KILL);
	}
	
}
