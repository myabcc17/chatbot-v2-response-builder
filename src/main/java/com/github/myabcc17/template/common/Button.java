package com.github.myabcc17.template.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.github.myabcc17.utils.StringValidator;
import lombok.Getter;

@Getter
public class Button {
    private final String label;
    private final String action;
    private String webLinkUrl;
    private String messageText;
    private String phoneNumber;
    private String blockId;
    private Map<String, Object> extra;

    private static final int MAX_LABEL = 14;
    private static final List<String> actionCadidates = List.of("webLink", "message", "block", "phone", "share", "operator", "addChannel");

    public enum Action {
        WEBLINK("webLink"),
        MESSAGE("message"),
        BLOCK("block"),
        PHONE("phone"),
        SHARE("share"),
        OPERATOR("operator"),
        ADD_CHANNEL("addChannel");

        private String value;

        Action(String value) {
            this.value = value;
        }
    }

    private Button(String label, Action action, String webLinkUrl, String messageText,
            String phoneNumber, String blockId,
            Map<String, Object> extra) {
        Objects.requireNonNull(label);
        Objects.requireNonNull(action);

        StringValidator.validateLength(label, MAX_LABEL);

        switch (action) {
            case WEBLINK -> Objects.requireNonNull(webLinkUrl);
            case MESSAGE -> Objects.requireNonNull(messageText);
            case PHONE -> Objects.requireNonNull(phoneNumber);
            case BLOCK -> Objects.requireNonNull(blockId);
        }

        this.label = label;
        this.action = action.value;
        this.webLinkUrl = webLinkUrl;
        this.messageText = messageText;
        this.phoneNumber = phoneNumber;
        this.blockId = blockId;
        this.extra = extra;
    }

    public static Button of(String label, Action action, String webLinkUrl, String messageText,
            String phoneNumber, String blockId,
            Map<String, Object> extra) {
        return new Button(label, action, webLinkUrl, messageText, phoneNumber, blockId, extra);
    }

    public static ButtonBuilder builder(String label, Action action) {
        return new ButtonBuilder(label, action);
    }

    public static class ButtonBuilder {
        private final String label;
        private final Action action;
        private String webLinkUrl;
        private String messageText;
        private String phoneNumber;
        private String blockId;
        private Map<String, Object> extra;

        public ButtonBuilder(String label, Action action) {
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
