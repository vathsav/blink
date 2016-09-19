package com.vathsav.blink.utils;

import android.content.Intent;

/**
 * App wide constants
 */
public class Constants {

    // Log Keys
    public static final String LOG_ERROR = "Blink_Error";

    // Activity Intents
    public static final Intent intentMainActivity = new Intent("com.vathsav.blink.MAIN");
    public static final Intent intentDetailsActivity = new Intent("com.vathsav.blink.DETAILS");
    public static final Intent intentNewLogActivity = new Intent("com.vathsav.blink.NEW_LOG");
}
