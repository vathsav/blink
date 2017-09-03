package com.vathsav.blink.utils;

import android.content.Intent;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseUser;

/**
 * App wide constants
 */
public class Constants {

    // Firebase References
    public static final String referenceLogs = "logs";
    public static final String referenceDrafts = "drafts";
    public static final String referencePreferences = "preferences";

    // Log Keys
    public static final String LOG_ERROR = "Blink_Error";

    // Activity Intents
    public static final Intent intentLoginActivity = new Intent("com.vathsav.blink.LOGIN");
    public static final Intent intentMainActivity = new Intent("com.vathsav.blink.MAIN");
    public static final Intent intentDetailsActivity = new Intent("com.vathsav.blink.DETAILS");
    public static final Intent intentNewTextLogActivity = new Intent("com.vathsav.blink.NEW_TEXT_LOG");
    public static final Intent intentNewAudioLogActivity = new Intent("com.vathsav.blink.NEW_AUDIO_LOG");
    public static final Intent intentNewVideoLogActivity = new Intent("com.vathsav.blink.NEW_VIDEO_LOG");
    public static final Intent intentAboutActivity = new Intent("com.vathsav.blink.ABOUT");
    public static final Intent intentSettingsActivity = new Intent("com.vathsav.blink.SETTINGS");

    // User Preferences
    public static String default_color = "cyan";

    // Login Globals
    public static GoogleApiClient googleApiClient;
    public static int googleLoginActivityResultCode = 2001;
}
