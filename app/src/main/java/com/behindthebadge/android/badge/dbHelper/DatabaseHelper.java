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
    private static final int DATABASE_VERSION = 1;

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
    public static final String RESPONDERS_EVENTS_RESPONDERS_ID = "RESPONDER_ID";
    public static final String RESPONDERS_EVENTS_EVENTS_ID = "EVENT_ID";

    public static final String RESPONDERS_RESPONSES_TABLE_NAME = "responders_responses";
    public static final String RESPONDERS_RESPONDES_RESPONDERS_ID = "RESPONDER_ID";
    public static final String RESPONDERS_RESPONSES_RESPONSE_ID = "RESPONSE_ID";

    public static final String RESPONSES_EVENTS_TABLE_NAME = "responses_events";
    public static final String RESPONSES_EVENTS_RESPONDERS_ID = "RESPONDER_ID";
    public static final String RESPONSES_EVENTS_RESPONSE_ID = "RESPONSE_ID";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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
    public long createResponder(Responder responder) {
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

                // adding to responders list
                responders.add(resp);
            } while (c.moveToNext());
        }

        return responders;
    }

    /*
     * getting all responders under single event
     * */
    public List<Responder> getAllRespondersByEvent(String event_name) {
        List<Responder> responders = new ArrayList<Responder>();

        String selectQuery = "SELECT  * FROM " + RESPONDERS_TABLE_NAME + " resp, "
                + EVENTS_TABLE_NAME + " evt, " + RESPONDERS_EVENTS_TABLE_NAME + " resp_evt WHERE evt."
                + EVENT_NAME + " = '" + event_name + "'" + " AND evt." + KEY_ID
                + " = " + "resp_evt." + RESPONDERS_EVENTS_EVENTS_ID + " AND resp." + KEY_ID + " = "
                + "resp_evt." + RESPONDERS_EVENTS_RESPONDERS_ID;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Responder td = new Responder();
                td.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                td.setName((c.getString(c.getColumnIndex(RESPONDERS_NAME))));
                td.setPhone(c.getString(c.getColumnIndex(RESPONDERS_PHONE)));

                responders.add(td);
            } while (c.moveToNext());
        }

        return responders;
    }

    /*
    * Updating a responder
    */
    public int updateResponder(Responder responder) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RESPONDERS_NAME, responder.getName());
        values.put(RESPONDERS_PHONE, responder.getPhone());

        // updating row
        return db.update(RESPONDERS_TABLE_NAME, values, KEY_ID + " = ?",
                new String[] { String.valueOf(responder.getId()) });
    }

    /*
    * Deleting a responder
    */
    public void deleteResponder(long responder_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(RESPONDERS_TABLE_NAME, KEY_ID + " = ?",
                new String[] { String.valueOf(responder_id) });
    }

    /*
    * Creating an event
    */
    public long createResponse(Response response, long responder_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RESPONSES_RESPONSE, response.getResponse());
        values.put(RESPONSES_EVENT, response.getEvent());

        // insert row
        long _id = db.insert(RESPONSES_TABLE_NAME, null, values);

        createResponderResponse(responder_id, _id);

        return _id;
    }

    /*
     * Creating ResponderResponse
     */
    public long createResponderResponse(long responder_id, long response_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RESPONDERS_RESPONDES_RESPONDERS_ID, responder_id);
        values.put(RESPONDERS_RESPONDES_RESPONDERS_ID, response_id);

        long id = db.insert(RESPONDERS_RESPONSES_TABLE_NAME, null, values);

        return id;
    }

    /*
    * get single event
    */
    public Response getResponse(long response_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + RESPONSES_TABLE_NAME + " WHERE "
                + KEY_ID + " = " + response_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Response response = new Response();
        response.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        response.setEvent(c.getString(c.getColumnIndex(RESPONSES_EVENT)));
        response.setResponse(c.getInt(c.getColumnIndex(RESPONSES_RESPONSE)));

        return response;
    }

    public List<Response> getAllResponses() {
        List<Response> responses = new ArrayList<Response>();
        String selectQuery = "SELECT  * FROM " + RESPONSES_TABLE_NAME;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Response response = new Response();
                response.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                response.setResponse((c.getInt(c.getColumnIndex(RESPONSES_RESPONSE))));
                response.setEvent(c.getString(c.getColumnIndex(RESPONSES_EVENT)));

                // adding to responses list
                responses.add(response);
            } while (c.moveToNext());
        }

        return responses;
    }

    /*
     * getting all responses under single responder
     * */
    public List<Event> getAllResponsesByResponder(String responder_name) {
        List<Event> events = new ArrayList<Event>();

        String selectQuery = "SELECT  * FROM " + EVENTS_TABLE_NAME + " evt, "
                + EVENTS_TABLE_NAME + " resp, " + RESPONDERS_EVENTS_TABLE_NAME + " resp_evt WHERE resp."
                + EVENT_NAME + " = '" + responder_name + "'" + " AND resp." + KEY_ID
                + " = " + "resp_evt." + RESPONDERS_EVENTS_EVENTS_ID + " AND evt." + KEY_ID + " = "
                + "resp_evt." + RESPONDERS_EVENTS_RESPONDERS_ID;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Event evt = new Event();
                evt.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                evt.setEventName((c.getString(c.getColumnIndex(EVENT_NAME))));
                evt.setDate(c.getString(c.getColumnIndex(EVENT_DATE)));

                events.add(evt);
            } while (c.moveToNext());
        }

        return events;
    }

    /*
     * Updating a response
     */
    public int updateResponses(Response response) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RESPONSES_EVENT, response.getEvent());
        values.put(RESPONSES_RESPONSE, response.getResponse());

        // updating row
        return db.update(EVENTS_TABLE_NAME, values, KEY_ID + " = ?",
                new String[] { String.valueOf(response.getId()) });
    }

    /*
     * Deleting a response
     */
    public void deleteResponse(long response_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(RESPONSES_TABLE_NAME, KEY_ID + " = ?",
                new String[] { String.valueOf(response_id) });
    }

     /*
     * Creating an event
     */
    public long createEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EVENT_NAME, event.getName());
        values.put(EVENT_DATE, event.getDate());

        // insert row
        long _id = db.insert(EVENTS_TABLE_NAME, null, values);

        return _id;
    }

    /*
    * get single event
    */
    public Event getEvent(long event_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + EVENTS_TABLE_NAME + " WHERE "
                + KEY_ID + " = " + event_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Event event = new Event();
        event.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        event.setEventName((c.getString(c.getColumnIndex(EVENT_NAME))));
        event.setDate(c.getString(c.getColumnIndex(EVENT_DATE)));

        return event;
    }

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<Event>();
        String selectQuery = "SELECT  * FROM " + EVENTS_TABLE_NAME;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Event event = new Event();
                event.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                event.setEventName((c.getString(c.getColumnIndex(EVENT_NAME))));
                event.setDate(c.getString(c.getColumnIndex(EVENT_DATE)));

                // adding to events list
                events.add(event);
            } while (c.moveToNext());
        }

        return events;
    }

    /*
     * getting all events under single responder
     * */
    public List<Event> getAllEventsByResponder(String responder_name) {
        List<Event> events = new ArrayList<Event>();

        String selectQuery = "SELECT  * FROM " + EVENTS_TABLE_NAME + " evt, "
                + EVENTS_TABLE_NAME + " resp, " + RESPONDERS_EVENTS_TABLE_NAME + " resp_evt WHERE resp."
                + EVENT_NAME + " = '" + responder_name + "'" + " AND resp." + KEY_ID
                + " = " + "resp_evt." + RESPONDERS_EVENTS_EVENTS_ID + " AND evt." + KEY_ID + " = "
                + "resp_evt." + RESPONDERS_EVENTS_RESPONDERS_ID;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Event evt = new Event();
                evt.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                evt.setEventName((c.getString(c.getColumnIndex(EVENT_NAME))));
                evt.setDate(c.getString(c.getColumnIndex(EVENT_DATE)));

                events.add(evt);
            } while (c.moveToNext());
        }

        return events;
    }

    /*
     * Updating an event
     */
    public int updateEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EVENT_NAME, event.getName());
        values.put(EVENT_DATE, event.getDate());

        // updating row
        return db.update(EVENTS_TABLE_NAME, values, KEY_ID + " = ?",
                new String[] { String.valueOf(event.getId()) });
    }

    /*
 * Deleting an event
 */
    public void deleteEvent(long event_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(EVENTS_TABLE_NAME, KEY_ID + " = ?",
                new String[] { String.valueOf(event_id) });
    }

    /*
     * Creating responder_event
     */
    public long createResponderEvent(long responder_id, long event_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RESPONDERS_EVENTS_RESPONDERS_ID, responder_id);
        values.put(RESPONDERS_EVENTS_EVENTS_ID, event_id);

        long id = db.insert(RESPONDERS_EVENTS_TABLE_NAME, null, values);

        return id;
    }

     /*
     * Updating a responder event
     */
    public int updateResponderEvent(long id, long event_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RESPONDERS_EVENTS_EVENTS_ID, event_id);

        // updating row
        return db.update(RESPONDERS_TABLE_NAME, values, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    public static String getDateTime(){
        Date presentTime_Date = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(presentTime_Date);
    }

}
