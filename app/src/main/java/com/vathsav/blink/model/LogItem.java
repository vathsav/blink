package com.vathsav.blink.model;

/**
 * Getter POJO for LogItem
 */

public class LogItem {
    private String log_title;
    private long log_timestamp;

    public LogItem() {}

    public LogItem( String log_title, long log_timestamp) {
        this.log_title = log_title;
        this.log_timestamp = log_timestamp;
    }

    public String get_log_title() {
        return log_title;
    }

    public long get_log_timestamp() {
        return log_timestamp;
    }
}
