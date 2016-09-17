package com.vathsav.blink.model;

/**
 * Getter POJO for CardItem
 */
public class CardItem {
    public String card_title;
    public long card_timestamp;

    public CardItem(String card_title, long card_timestamp) {
        this.card_title = card_title;
        this.card_timestamp = card_timestamp;
    }

    public String get_card_title() {
        return card_title;
    }

    public long get_card_timestamp() {
        return card_timestamp;
    }
}
