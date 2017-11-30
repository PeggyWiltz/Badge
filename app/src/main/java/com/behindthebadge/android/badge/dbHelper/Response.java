package com.behindthebadge.android.badge.dbHelper;

/**
 * Created by us50416 on 11/26/17.
 */

public class Response {

    int id;
    String event;
    int response;

    // constructors
    public Response() {
    }

    public Response(String event, int response) {
        this.event = event;
        this.response = response;
    }

    public Response(int id, String event, int response) {
        this.id = id;
        this.event = event;
        this.response = response;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setResponse(int response) {
        this.response = response;
    }

    // getters
    public long getId() {
        return this.id;
    }

    public String getEvent() {
        return this.event;
    }

    public int getResponse() {
        return this.response;
    }
}
