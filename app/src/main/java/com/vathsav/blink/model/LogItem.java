package com.vathsav.blink.model;

/**
 * Getter POJO for LogItem
 */

public class LogItem {
    private String log_key;
    private String log_title;
    private String log_content;
    private long log_timestamp;

    public LogItem() {
    }

    public LogItem(String log_key, String log_title, String log_content, long log_timestamp) {
        this.log_key = log_key;
        this.log_title = log_title;
        this.log_content = log_content;
        this.log_timestamp = log_timestamp;
    }

    public String get_log_key() {
        return log_key;
    }

    public String get_log_title() {
        return log_title;
    }

    public String get_log_content() {
        return log_content;
    }

    public long get_log_timestamp() {
        return log_timestamp;
    }
}
