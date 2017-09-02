package com.vathsav.blink.model;

/**
 * Setter class for inserting drafts into Firebase
 */

@SuppressWarnings("WeakerAccess")
public class DraftItemSetter {

    public final String draft_title;
    public final String draft_content;
    public final String draft_color;
    public final Object draft_timestamp;

    public DraftItemSetter(String draft_title, String draft_content, String draft_color, Object draft_timestamp) {
        this.draft_title = draft_title;
        this.draft_content = draft_content;
        this.draft_color = draft_color;
        this.draft_timestamp = draft_timestamp;
    }
}
