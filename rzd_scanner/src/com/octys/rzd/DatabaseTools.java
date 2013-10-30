package com.octys.rzd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseTools {

	private static final String TAG = DatabaseTools.class.getName();
	
	private static DatabaseTools dbTools=null;
	
	private DatabaseHelper helper;
	private SQLiteDatabase db;
	
	public DatabaseTools(Context c) {
		helper = new DatabaseHelper(c);
		open();
	}
	
	private void open() {
		db = helper.getWritableDatabase();
	}
	
	public void close() {
		TicketScannerApplication.stackTrace();
		if (db.isOpen())
			helper.close();
	}

    public synchronized void putData(String rr, String lt, String personalIdString) {
    	try {
    		if (!db.isOpen()) open();
    		helper.put(db, helper.new Record(rr, lt, personalIdString));
    	} catch (Throwable t) { Log.e(TAG, "putData: "+t); }
    }
    
    public synchronized void delData(int id) {
    	try {
    		if (!db.isOpen()) open();
    		helper.del(db, id);
    	} catch (Throwable t) {}
    }
	
	public int getCount() {
		if (!db.isOpen()) open();
		return helper.getCount(db);
	}
	
	public DatabaseHelper.RecordIterator getAll() {
		if (!db.isOpen()) open();
		return helper.getAll(db);
	}
	
	public static DatabaseTools getDatabaseTools(Context c) {
		if (dbTools==null) dbTools=new DatabaseTools(c);
		return dbTools;
	}
	
}
