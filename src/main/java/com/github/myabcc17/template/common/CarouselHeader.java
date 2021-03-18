package com.github.myabcc17.template.common;

import java.util.Objects;

public class CarouselHeader {
    private final String title;
    private final String description;
    private final Thumbnail thumbnail;

    private CarouselHeader(String title, String description, Thumbnail thumbnail) {
        Objects.requireNonNull(thumbnail);
        Objects.requireNonNull(description);
        Objects.requireNonNull(thumbnail);

        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
    }

    public static CarouselHeader of(String title, String description, Thumbnail thumbnail) {
        return new CarouselHeader(title, description, thumbnail);
    }
}
