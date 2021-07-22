package com.github.myabcc17.template;

import lombok.Getter;

import java.util.Map;
import java.util.Objects;

@Getter
public class QuickReply {
    private final String label;
    private final String action;
    private String messageText;
    private String blockId;
    private Map<String, Object> extra;

    public enum Action {
        MESSAGE("message"),
        BLOCK("block");

        private String value;

        Action(String value) {
            this.value = value;
        }
    }

    private QuickReply(String label, Action action, String messageText, String blockId,
            Map<String, Object> extra) {
        Objects.requireNonNull(label);
        Objects.requireNonNull(action);

        switch (action) {
            case MESSAGE -> Objects.requireNonNull(messageText);
            case BLOCK -> {
                Objects.requireNonNull(messageText);
                Objects.requireNonNull(blockId);
            }
        }

        this.label = label;
        this.action = action.value;
        this.messageText = messageText;
        this.blockId = blockId;
        this.extra = extra;
    }

    public static QuickReply of(String label, Action action, String messageText, String blockId, Map<String, Object> extra) {
        return new QuickReply(label, action, messageText, blockId, extra);
    }

    public static QuickReplyBuilder builder(String label, Action action) {
        return new QuickReplyBuilder(label, action);
    }

    public static class QuickReplyBuilder {
        private final String label;
        private final Action action;
        private String messageText;
        private String blockId;
        private Map<String, Object> extra;

        public QuickReplyBuilder(String label, Action action) {
            this.label = label;
            this.action = action;
        }

        public QuickReplyBuilder messageText(String messageText) {
            this.messageText = messageText;
            return this;
        }

        public QuickReplyBuilder blockId(String blockId) {
            this.blockId = blockId;
            return this;
        }

        public QuickReplyBuilder extra(Map<String, Object> extra) {
            this.extra = extra;
            return this;
        }

        public QuickReply build() {
            return new QuickReply(label, action, messageText, blockId, extra);
        }
    }
}
