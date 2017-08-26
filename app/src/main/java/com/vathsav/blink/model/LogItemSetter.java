package com.vathsav.blink.model;

/**
 * Created by vathsav on 26/08/17.
 */

public class LogItemSetter {

    public String log_title;
    public String log_content;
    public Object log_timestamp;

    public LogItemSetter(String log_title, String log_content, Object log_timestamp) {
        this.log_title = log_title;
        this.log_content = log_content;
        this.log_timestamp = log_timestamp;
    }
}
