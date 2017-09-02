package com.vathsav.blink.utils;

import com.vathsav.blink.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by vathsav on 01/10/16.
 * Class for app-wide utility methods and data members
 */

public class Utils {

    public static void initializeCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Lato-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
