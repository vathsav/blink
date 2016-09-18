package com.vathsav.blink.model;

/**
 * Getter POJO for LogItem
 */

public class LogItem {
    public String log_key;
    public String log_title;
    public long log_timestamp;

    public LogItem(String log_key, String log_title, long log_timestamp) {
        this.log_key = log_key;
        this.log_title = log_title;
        this.log_timestamp = log_timestamp;
    }

    public String get_log_key() {
        return log_key;
    }

    public String get_log_title() {
        return log_title;
    }

    public long get_log_timestamp() {
        return log_timestamp;
    }
}
