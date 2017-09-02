package com.vathsav.blink.model;

/**
 * Getter POJO for LogItem
 */

public class DraftItem {
    private String draft_key;
    private String draft_title;
    private String draft_content;
    private String draft_color;
    private long draft_timestamp;

    public DraftItem() {
    }

    public DraftItem(String draft_key, String draft_title, String draft_content, String draft_color, long draft_timestamp) {
        this.draft_key = draft_key;
        this.draft_title = draft_title;
        this.draft_content = draft_content;
        this.draft_color = draft_color;
        this.draft_timestamp = draft_timestamp;
    }

    public String get_draft_key() {
        return draft_key;
    }

    public String get_draft_title() {
        return draft_title;
    }

    public String get_draft_content() {
        return draft_content;
    }

    public String get_draft_color() {
        return draft_color;
    }

    public long get_draft_timestamp() {
        return draft_timestamp;
    }
}
