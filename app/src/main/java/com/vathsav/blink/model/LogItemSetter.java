package com.vathsav.blink.model;

/**
 * Setter class for inserting logs into Firebase
 */

@SuppressWarnings("WeakerAccess")
public class LogItemSetter {

    public final String log_title;
    public final String log_content;
    public final boolean log_favorite;
    public final String log_color;
    public final Object log_timestamp;

    public LogItemSetter(String log_title, String log_content, boolean log_favorite, String log_color, Object log_timestamp) {
        this.log_title = log_title;
        this.log_content = log_content;
        this.log_favorite = log_favorite;
        this.log_color = log_color;
        this.log_timestamp = log_timestamp;
    }
}
