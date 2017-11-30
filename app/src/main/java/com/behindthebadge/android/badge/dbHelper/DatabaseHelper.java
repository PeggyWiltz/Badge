package com.behindthebadge.android.badge.dbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.behindthebadge.android.badge.dbHelper.Responder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by us50416 on 11/26/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    public static final String DATABASE_NAME = "BehindTheBadge.db";
    public static final String RESPONDERS_TABLE_NAME = "responders";
    public static final String KEY_ID = "ID";
    public static final String RESPONDERS_NAME = "NAME";
    public static final String RESPONDERS_PHONE = "PHONE";

    public static final String RESPONSES_TABLE_NAME = "responses";
    public static final String RESPONSES_EVENT = "EVENT";
    public static final String RESPONSES_RESPONSE = "RESPONSE";

    public static final String EVENTS_TABLE_NAME = "events";
    public static final String EVENT_NAME = "NAME";
    public static final String EVENT_DATE = "DATE";

    public static final String RESPONDERS_EVENTS_TABLE_NAME = "responders_events";
    public static final String RESPONDERS_EVENTS_RESPONDERS_ID = "RESPONDERS_ID";
    public static final String RESPONDERS_EVENTS_EVENTS_ID = "EVENTS_ID";

    public static final String RESPONDERS_RESPONSES_TABLE_NAME = "responders_responses";
    public static final String RESPONDERS_RESPONDES_RESPONDERS_ID = "RESPONDERS_ID";
    public static final String RESPONDERS_RESPONSES_RESPONSE_ID = "RESPONSE_ID";

    public static final String RESPONSES_EVENTS_TABLE_NAME = "responses_events";
    public static final String RESPONSES_EVENTS_RESPONDERS_ID = "RESPONDERS_ID";
    public static final String RESPONSES_EVENTS_RESPONSE_ID = "RESPONSE_ID";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + RESPONDERS_TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,PHONE TEXT)");
        db.execSQL("create table " + RESPONSES_TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, EVENT TEXT, RESPONSE INTEGER)");
        db.execSQL("create table " + EVENTS_TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, DATE TEXT)");

        db.execSQL("create table " + RESPONDERS_EVENTS_TABLE_NAME + " (KEY_ID INTEGER PRIMARY KEY, RESPONDER_ID INTEGER, EVENT_ID INTEGER)");
        db.execSQL("create table " + RESPONDERS_RESPONSES_TABLE_NAME + " (ID INTEGER PRIMARY KEY, RESPONDER_ID INTEGER, RESPONSE_ID INTEGER)");
        db.execSQL("create table " + RESPONSES_EVENTS_TABLE_NAME + " (ID INTEGER PRIMARY KEY, RESPONSE_ID INTEGER, EVENT_ID INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RESPONDERS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RESPONSES_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + EVENTS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RESPONDERS_EVENTS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RESPONDERS_RESPONSES_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RESPONSES_EVENTS_TABLE_NAME);
        onCreate(db);
    }

    /*
     * Creating a responder
     */
    public long createResponder(Responder responder, long[] responder_ids) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RESPONDERS_NAME, responder.getName());
        values.put(RESPONDERS_PHONE, responder.getPhone());

        // insert row
        long _id = db.insert(RESPONDERS_TABLE_NAME, null, values);

        return _id;
    }

    /*
 * get single todo
 */
    public Responder getResponder(long responder_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + RESPONDERS_TABLE_NAME + " WHERE "
                + KEY_ID + " = " + responder_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Responder responder = new Responder();
        responder.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        responder.setName((c.getString(c.getColumnIndex(RESPONDERS_NAME))));
        responder.setPhone(c.getString(c.getColumnIndex(RESPONDERS_PHONE)));

        return responder;
    }

    public List<Responder> getAllResponders() {
        List<Responder> responders = new ArrayList<Responder>();
        String selectQuery = "SELECT  * FROM " + RESPONDERS_TABLE_NAME;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Responder resp = new Responder();
                resp.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                resp.setName((c.getString(c.getColumnIndex(RESPONDERS_NAME))));
                resp.setPhone(c.getString(c.getColumnIndex(RESPONDERS_PHONE)));

                // adding to todo list
                responders.add(resp);
            } while (c.moveToNext());
        }

        return responders;
    }



    public static String getDateTime(){
        Date presentTime_Date = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(presentTime_Date);
    }

}
