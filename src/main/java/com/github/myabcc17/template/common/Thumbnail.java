package com.github.myabcc17.template.common;

import java.util.Objects;

import com.github.myabcc17.exception.InvalidUrlException;
import lombok.Getter;
import org.apache.commons.lang3.BooleanUtils;
import com.github.myabcc17.utils.UrlUtils;

@Getter
public class Thumbnail {
    private final String imageUrl;
    private final Link link;
    private final Boolean fixedRatio;
    private final Integer width;
    private final Integer height;

    public Thumbnail(String imageUrl, Link link, Boolean fixedRatio, Integer width, Integer height) {
        Objects.requireNonNull(imageUrl);

        if (!UrlUtils.isValidUrl(imageUrl)) {
            throw new InvalidUrlException();
        }

        if (BooleanUtils.isTrue(fixedRatio)) {
            Objects.requireNonNull(width);
            Objects.requireNonNull(height);
        }

        this.imageUrl = imageUrl;
        this.link = link;
        this.fixedRatio = fixedRatio;
        this.width = width;
        this.height = height;
    }

    public static ThumbnailBuilder builder(String imageUrl) {
        return new ThumbnailBuilder(imageUrl);
    }

    public static class ThumbnailBuilder {
        private final String imageUrl;
        private Link link;
        private Boolean fixedRatio;
        private Integer width;
        private Integer height;

        public ThumbnailBuilder(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public ThumbnailBuilder link(Link link) {
            this.link = link;
            return this;
        }

        public ThumbnailBuilder fixedRatio(boolean fixedRatio) {
            this.fixedRatio = fixedRatio;
            return this;
        }

        public ThumbnailBuilder width(int width) {
            this.width = width;
            return this;
        }

        public ThumbnailBuilder height(int height) {
            this.height = height;
            return this;
        }

        public Thumbnail build() {
            return new Thumbnail(imageUrl, link, fixedRatio, width, height);
        }
    }
}
