package com.vathsav.blink.model;

/**
 * Getter POJO for LogItem
 */

public class LogItem {
    private String log_key;
    private String log_title;
    private String log_content;
    private boolean log_favorite;
    private String log_color;
    private long log_timestamp;

    public LogItem() {
    }

    public LogItem(String log_key, String log_title, String log_content, boolean log_favorite, String log_color, long log_timestamp) {
        this.log_key = log_key;
        this.log_title = log_title;
        this.log_content = log_content;
        this.log_favorite = log_favorite;
        this.log_color = log_color;
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

    public boolean is_log_favorite() {
        return log_favorite;
    }

    public String get_log_color() {
        return log_color;
    }

    public long get_log_timestamp() {
        return log_timestamp;
    }
}
