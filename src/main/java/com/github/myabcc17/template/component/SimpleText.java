package com.github.myabcc17.template.component;

import com.github.myabcc17.utils.StringValidator;
import lombok.Getter;

import java.util.Objects;

@Getter
public class SimpleText extends Component {
    private final String text;

    private static final int MAX_TEXT_LENGTH = 1000;

    private SimpleText(String text) {
        super("simpleText");

        Objects.requireNonNull(text);

        StringValidator.validateLength("text", MAX_TEXT_LENGTH);

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
