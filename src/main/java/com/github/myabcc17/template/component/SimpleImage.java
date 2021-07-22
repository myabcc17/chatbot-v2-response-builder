package com.github.myabcc17.template.component;

import com.github.myabcc17.utils.StringValidator;
import lombok.Getter;

import java.util.Objects;

@Getter
public class SimpleImage extends Component {
    private final String imageUrl;
    private final String altText;

    private static final int MAX_ALT_TEXT = 1000;

    private SimpleImage(String imageUrl, String altText) {
        super("simpleImage");

        Objects.requireNonNull(imageUrl);
        Objects.requireNonNull(altText);

        StringValidator.validateLength("altText", MAX_ALT_TEXT);

        this.imageUrl = imageUrl;
        this.altText = altText;
    }

    public static SimpleImage of(String imageUrl, String altText) {
        return new SimpleImage(imageUrl, altText);
    }

    public static SimpleImageBuilder builder(String imageUrl, String altText) {
        return new SimpleImageBuilder(imageUrl, altText);
    }

    public static class SimpleImageBuilder {
        private final String imageUrl;
        private final String altText;

        public SimpleImageBuilder(String imageUrl, String altText) {
            this.imageUrl = imageUrl;
            this.altText = altText;
        }

        public SimpleImage build() {
            return new SimpleImage(imageUrl, altText);
        }
    }
}
