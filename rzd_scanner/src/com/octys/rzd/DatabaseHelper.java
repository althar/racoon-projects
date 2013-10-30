package com.octys.rzd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = DatabaseHelper.class.getName();

    private static final String DATABASE_NAME = "ticket_scanner.db";
    private static final String TAB_TICKET_PAIRS = "ticket_pairs";

    private static final String COL_ID = "_id";
    private static final String COL_DATE = "date";
    private static final String COL_OPERATOR_ID = "operator_id";
    private static final String COL_RAILROAD_TICKET = "railroad_ticket";
    private static final String COL_LOTTERY_TICKET = "lottery_ticket";
    private static final String COL_SENT = "sent";

//	private static SimpleDateFormat sdf = new SimpleDateFormat(Consts.DATE_FORMAT);

    public class RecordIterator implements Iterable<Record>, Iterator<Record> {
        private Cursor c;
        private int count=0;

        public RecordIterator(Cursor aC) {
            c = aC;
            if (aC==null || !aC.moveToFirst()) return;
            count = aC.getCount();
        }

        public void close() {
            if (c!=null)
                c.close();
        }

        @Override
        public boolean hasNext() {
            return count>0;
        }

        @Override
        public Record next() throws NoSuchElementException {
            if (count==0) throw new NoSuchElementException();
            Record r = new Record(c);
            c.moveToNext();
            count--;
            return r;
        }

        @Override
        public void remove() {
        }

        @Override
        public Iterator<Record> iterator() {
            return this;
        }

    }

    public class Record {
        int id;
        String date;
        String operator_id;
        String railroad_ticket;
        String lottery_ticket;
        boolean sent;

        public Record() {
        }

        public Record(Cursor c) {
            if (c.isAfterLast()) return;
            id = c.getInt(c.getColumnIndex(COL_ID));
            date = c.getString(c.getColumnIndex(COL_DATE));
            operator_id = c.getString(c.getColumnIndex(COL_OPERATOR_ID));
            railroad_ticket = c.getString(c.getColumnIndex(COL_RAILROAD_TICKET));
            lottery_ticket = c.getString(c.getColumnIndex(COL_LOTTERY_TICKET));
            sent = c.getInt(c.getColumnIndex(COL_SENT))>0;
        }

        public Record(String r, String l, String id) {
            this.id = -1;
            this.date = Long.toString(System.currentTimeMillis()/1000L); //sdf.format(new Date());
            this.operator_id = id;
            this.railroad_ticket = r;
            this.lottery_ticket = l;
            this.sent = false;

            if (empty(this.date)) Log.e(TAG, "New record with empty field 'date'");
            if (empty(this.operator_id)) Log.e(TAG, "New record with empty field 'operator_id'");
            if (empty(this.railroad_ticket)) Log.e(TAG, "New record with empty field 'railroad_ticket'");
            if (empty(this.lottery_ticket)) Log.e(TAG, "New record with empty field 'lottery_ticket'");
        }

        public boolean empty(String s) {
            return s==null || s.length()==0;
        }

        void check_n_add_pair(List<NameValuePair> l, String name, String v) {
            l.add(new BasicNameValuePair(name, v));
            if (empty(v)) Log.e(TAG, "Empty data "+name);
        }

        public List<NameValuePair> getList(String imei/*imei_md5*/, String fingerprint) {
            List<NameValuePair> l = new ArrayList<NameValuePair>(7);
            check_n_add_pair(l, "request_id",Integer.toString(id));
            check_n_add_pair(l, "ticket_id",railroad_ticket);
            check_n_add_pair(l, "lot_ticket_id",lottery_ticket);
            check_n_add_pair(l, "operator_id",operator_id);
            check_n_add_pair(l, "date",date);
            check_n_add_pair(l, "hash",imei);  // imei_md5
            if (fingerprint!=null && fingerprint.length()>0)
                check_n_add_pair(l, "fingerprint", fingerprint);
            return l;
        }
    }

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, Consts.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TAB_TICKET_PAIRS + "(" +
                COL_ID					+ " INTEGER primary key autoincrement, " +
                COL_DATE				+ " TEXT, "	+
                COL_OPERATOR_ID			+ " TEXT, " +
                COL_RAILROAD_TICKET		+ " TEXT, " +
                COL_LOTTERY_TICKET		+ " TEXT, " +
                COL_SENT				+ " INTEGER"+
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "upgrading database from v."+oldVersion+" to v."+newVersion);
        db.execSQL("DROP TABLE IF EXISTS "+TAB_TICKET_PAIRS);
        onCreate(db);
    }

    public void put(SQLiteDatabase db, Record r) {
        ContentValues cv = new ContentValues();
        cv.put(COL_DATE, r.date);
        cv.put(COL_OPERATOR_ID, r.operator_id);
        cv.put(COL_RAILROAD_TICKET, r.railroad_ticket);
        cv.put(COL_LOTTERY_TICKET, r.lottery_ticket);
        cv.put(COL_SENT, r.sent ? 1 : 0);
//		Log.w(TAG, "put record "+cv);
        db.insert(TAB_TICKET_PAIRS, COL_ID, cv);
    }

    public Record getTop(SQLiteDatabase db) {
        String select = "select * from "+TAB_TICKET_PAIRS+" where "+COL_SENT+"=0 limit 1;";
        Cursor cursor = db.rawQuery(select, null);
//		if (cursor.getCount()==0) return null;
        if (!cursor.moveToFirst()) return null;
        Record r = new Record(cursor);
        cursor.close();
        return r;
    }

    public void markSent(SQLiteDatabase db, Record r) {
        ContentValues cv = new ContentValues();
        cv.put(COL_SENT, "1");
        db.update(TAB_TICKET_PAIRS, cv, COL_ID+"=?", new String[] {String.valueOf(r.id)});
        r.sent=true;
    }

    public int getCount(SQLiteDatabase db) {
        Cursor cursor= db.query(TAB_TICKET_PAIRS,new String[] {COL_ID},COL_SENT+"=0", null, null, null, null);
        cursor.moveToFirst();
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public RecordIterator getAll(SQLiteDatabase db) {
        Cursor cursor;
        try {
            cursor= db.query(TAB_TICKET_PAIRS,null,COL_SENT+"=0", null, null, null, null);
            Log.w(TAG, "cursor.getCount()="+cursor.getCount());
        } catch (Exception e) {
            cursor = null;
            Log.e(TAG, "getAll() error:" + e);
        }
        return new RecordIterator(cursor);
    }

    public void del(SQLiteDatabase db, Record r) {
        del(db, r.id);
    }

    public void del(SQLiteDatabase db, int id) {
        db.delete(TAB_TICKET_PAIRS, COL_ID + "=?", new String[] { String.valueOf(id) });
    }

}
