package com.octys.rzd;

import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import java.io.*;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class SDCardLogger {
	private static final String TAG = SDCardLogger.class.getName();

	private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss ");
	ByteArrayOutputStream a = new ByteArrayOutputStream();
	private PrintWriter writer = new PrintWriter(a, true);

	private String path = null;
	private long maxFileSize;
	private File f = null;
	
	public SDCardLogger(String aPath, long maxFileSize) {
		path = aPath;
		this.maxFileSize = maxFileSize;
	}

	public void flush() {
		try {
			if (a.size()==0) return;
			if (!isSDPresent()) return;

			File dir = new File(Environment.getExternalStorageDirectory(), path);
			if (!dir.exists() && !dir.mkdirs()) {
				Log.e(TAG, "Could not create log directory: " + dir.getAbsolutePath());
				return;
			}
			
			if (f==null || !f.exists() || f.length()>=maxFileSize)
				f = newFile(dir);
				
			Log.i(TAG, "Opening " + f.getAbsolutePath());
			boolean append = true;
			FileOutputStream s = new FileOutputStream(f, append);
			synchronized (this) {
				a.writeTo(s);
				a.reset();
			}
			s.flush();
			s.close();
		} catch (IOException ioe) {
			Log.e(TAG, "Failed while opening the log file.", ioe);
		}
	}

	private File newFile(File dir) {
		File f;
		String logFilePrefix = getFilePrefix();
		int i = 0;
		do {
			f = new File(dir, logFilePrefix+(i>0?"_"+i:"")+".txt");
			i++;
		} while (f.exists());
		return f;
	}
	
	public static boolean isSDPresent() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}
	
	public static double availableSize() {
		try {
			StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
			return (double)stat.getAvailableBlocks() * (double)stat.getBlockSize();
		} catch (Exception e) { return 0; }
	}

	public String getFilePrefix() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm", Locale.getDefault());
		return formatter.format(new GregorianCalendar().getTime());
	}
		
	public synchronized void log(String message, Object... parameters) {
		try {
	        writer.print( format.format(new Date()));
	        if( parameters.length > 0 ) {
	            writer.println( MessageFormat.format( message, parameters ) );
		        Log.w(TAG, MessageFormat.format( message, parameters ));
	        } else {
	            writer.println( message );
		        Log.w(TAG, message);
	        }
	        writer.flush();
		} catch (Throwable T) {}
	}
}
