package com.behindthebadge.android.badge.screens;

/**
 * Created by us50416 on 5/23/17.
 */

public class ResponseModel {



    public enum ResponseStatus {
        YES,
        NO,
        MAYBE,
        NOT_RESPONDED
    }

    private String name;
    private String organization;
    private ResponseStatus status;


    public ResponseModel(String name, String organization, ResponseStatus status) {
        this.name = name;
        this.organization = organization;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getOrganization() {
        return organization;
    }

    public ResponseStatus getStatus() {
        return status;
    }
}
