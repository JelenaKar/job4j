package ru.job4j.autosale.model;

public enum Filters {
    PER_DAY("per-day"),
    WITH_IMGS_ONLY("imgs-only"),
    GIVEN_BRAND_ONLY("brand");

    private String text;

    Filters(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static Filters fromString(String text) {
        for (Filters f : Filters.values()) {
            if (f.text.equalsIgnoreCase(text)) {
                return f;
            }
        }
        return null;
    }
}
