package com.behindthebadge.android.badge.dbHelper;

/**
 * Created by us50416 on 11/26/17.
 */

public class Responder {

    int id;
    String name;
    String phone;

    // constructors
    public Responder() {
    }

    public Responder(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Responder(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // getters
    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPhone() {
        return this.phone;
    }

}
