package com.github.myabcc17.template.component;

import java.util.Objects;

import com.github.myabcc17.template.common.Link;
import lombok.Getter;

@Getter
public class ListItem {
    private final String title;
    private String description;
    private String imageUrl;
    private Link link;

    private ListItem(String title, String description, String imageUrl, Link link) {
        Objects.requireNonNull(title);

        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.link = link;
    }

    public static ListItem of(String title) {
        return new ListItem(title, null, null, null);
    }

    public static ListItem of(String title, String description, String imageUrl, Link link) {
        return new ListItem(title, description, imageUrl, link);
    }

    public static ListItemBuilder builder(String title) {
        return new ListItemBuilder(title);
    }

    public static class ListItemBuilder {
        private final String title;
        private String description;
        private String imageUrl;
        private Link link;

        public ListItemBuilder(String title) {
            this.title = title;
        }

        public ListItemBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ListItemBuilder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public ListItemBuilder link(Link link) {
            this.link = link;
            return this;
        }

        public ListItem build() {
            return new ListItem(title, description, imageUrl, link);
        }
    }
}
