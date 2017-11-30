package com.behindthebadge.android.badge.dbHelper;

/**
 * Created by us50416 on 11/26/17.
 */

public class Event {
    int id;
    String name;
    String date;

    // constructors
    public Event() {
    }

    public Event(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public Event(int id, String name, String date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setEventName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // getters
    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDate() {
        return this.date;
    }
}
