package com.github.myabcc17.template.component;

import java.util.Objects;

public class SimpleText extends Component {
    private final String text;

    private static final int MAX_TEXT_LENGTH = 1000;

    private SimpleText(String text) {
        super("simpleText");

        Objects.requireNonNull(text);

        if (text.length() > MAX_TEXT_LENGTH) {
            throw new RuntimeException(String.format("최대 길이 %d자를 초과했습니다.", MAX_TEXT_LENGTH));
        }
        this.text = text;
    }

    public static SimpleText of(String text) {
        return new SimpleText(text);
    }

    public static SimpleTextBuilder builder(String text) {
        return new SimpleTextBuilder(text);
    }

    public static class SimpleTextBuilder {
        private final String text;

        public SimpleTextBuilder(String text) {
            this.text = text;
        }

        public SimpleText build() {
            return new SimpleText(text);
        }
    }
}
