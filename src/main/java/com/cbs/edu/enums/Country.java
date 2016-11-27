package com.cbs.edu.enums;

public enum Country {
    USA("United states of America"),
    UKRAINE("Ukraine");

    private String title;

    Country(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
