package com.github.myabcc17.template.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import com.github.myabcc17.utils.StringValidator;
import com.github.myabcc17.utils.UrlUtils;

public class Button {
    private final String label;
    private final String action;
    private String webLinkUrl;
    private String messageText;
    private String phoneNumber;
    private String blockId;
    private Map<String, Object> extra;

    private static final int MAX_LABEL = 14;
    private static final List<String> actionCadidates = List.of("webLink", "message", "block", "phone", "share", "operator");

    private Button(String label, String action, String webLinkUrl, String messageText,
            String phoneNumber, String blockId,
            Map<String, Object> extra) {
        Objects.requireNonNull(label);
        Objects.requireNonNull(action);

        StringValidator.validateLength(label, MAX_LABEL);

        if (!actionCadidates.contains(action)) {
            throw new RuntimeException(String.format("action은 해당 값 중 하나만 가능합니다. %s", actionCadidates));
        }

        switch (action) {
            case "webLink" -> {
                Objects.requireNonNull(webLinkUrl);
                if (UrlUtils.isValidUrl(webLinkUrl)) {
                    throw new RuntimeException("URL 형식이 아닙니다.");
                }
            }
            case "message" -> Objects.requireNonNull(messageText);
            case "phone" -> Objects.requireNonNull(phoneNumber);
            case "block" -> {
                Objects.requireNonNull(webLinkUrl);
                if (UrlUtils.isValidUrl(webLinkUrl)) {
                    throw new RuntimeException("URL 형식이 아닙니다.");
                }
                Objects.requireNonNull(blockId);
            }
        }

        this.label = label;
        this.action = action;
        this.webLinkUrl = webLinkUrl;
        this.messageText = messageText;
        this.phoneNumber = phoneNumber;
        this.blockId = blockId;
        this.extra = extra;
    }

    public static Button of(String label, String action, String webLinkUrl, String messageText,
            String phoneNumber, String blockId,
            Map<String, Object> extra) {
        return new Button(label, action, webLinkUrl, messageText, phoneNumber, blockId, extra);
    }

    public static ButtonBuilder builder(String label, String action) {
        return new ButtonBuilder(label, action);
    }

    public static class ButtonBuilder {
        private final String label;
        private final String action;
        private String webLinkUrl;
        private String messageText;
        private String phoneNumber;
        private String blockId;
        private Map<String, Object> extra;

        public ButtonBuilder(String label, String action) {
            this.label = label;
            this.action = action;
        }

        public ButtonBuilder webLinkUrl(String webLinkUrl) {
            this.webLinkUrl = webLinkUrl;
            return this;
        }

        public ButtonBuilder messageText(String messageText) {
            this.messageText = messageText;
            return this;
        }

        public ButtonBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public ButtonBuilder blockId(String blockId) {
            this.blockId = blockId;
            return this;
        }

        public ButtonBuilder extra(Map<String, Object> extra) {
            this.extra = extra;
            return this;
        }

        public ButtonBuilder putOneExtra(String key, Object value) {
            if (extra == null) {
                extra = new HashMap<>();
            }
            extra.put(key, value);
            return this;
        }

        public Button build() {
            return new Button(label, action, webLinkUrl, messageText, phoneNumber, blockId, extra);
        }
    }
}
