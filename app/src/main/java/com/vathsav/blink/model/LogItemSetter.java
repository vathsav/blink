package com.vathsav.blink.model;

/**
 * Created by vathsav on 26/08/17.
 */

public class LogItemSetter {

    public String log_title;
    public long log_timestamp;

    public LogItemSetter(String log_title, long log_timestamp) {
        this.log_title = log_title;
        this.log_timestamp = log_timestamp;
    }
}
