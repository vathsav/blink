package com.vathsav.blink.model;

/**
 * Created by vathsav on 26/08/17.
 */

public class LogItemSetter {

    public String log_title;
    public String log_content;
    public boolean log_favorite;
    public String log_color;
    public Object log_timestamp;

    public LogItemSetter(String log_title, String log_content, boolean log_favorite, String log_color, Object log_timestamp) {
        this.log_title = log_title;
        this.log_content = log_content;
        this.log_favorite = log_favorite;
        this.log_color = log_color;
        this.log_timestamp = log_timestamp;
    }
}
