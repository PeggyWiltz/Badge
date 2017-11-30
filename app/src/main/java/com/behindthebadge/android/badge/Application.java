package com.behindthebadge.android.badge;

import android.support.multidex.MultiDexApplication;

import com.amazonaws.mobile.auth.core.IdentityManager;
import com.amazonaws.mobile.config.AWSConfiguration;

/**
 * Created by us50416 on 9/14/17.
 */

public class Application extends MultiDexApplication {
    private static final String LOG_TAG = Application.class.getSimpleName();


    @Override
    public void onCreate() {
        super.onCreate();
        initializeApplication();

    }

    private void initializeApplication() {

    }
}
