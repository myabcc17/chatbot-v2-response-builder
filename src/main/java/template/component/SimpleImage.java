package template.component;

import java.util.Objects;
import utils.UrlUtils;

public class SimpleImage extends Component {
    private final String imageUrl;
    private final String altText;

    private static final int MAX_ALT_TEXT = 1000;

    private SimpleImage(String imageUrl, String altText) {
        super("simpleImage");

        Objects.requireNonNull(imageUrl);
        Objects.requireNonNull(altText);

        if (UrlUtils.isValidUrl(imageUrl)) {
            throw new RuntimeException("URL 형식이 아닙니다.");
        }

        if (altText.length() >= MAX_ALT_TEXT) {
            throw new RuntimeException(String.format("altText는 최대 %d자 입니다.", MAX_ALT_TEXT));
        }

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
