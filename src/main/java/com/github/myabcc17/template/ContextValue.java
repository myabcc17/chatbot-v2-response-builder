package com.github.myabcc17.template;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ContextValue {
    private final String name;
    private final int lifeSpan;
    private Map<String, String> params;

    private ContextValue(String name, int lifeSpan,
            Map<String, String> params) {
        if (StringUtils.isEmpty(name)) {
            throw new RuntimeException("contextValue name cannot be empty");
        }

        if (lifeSpan < 0) {
            throw new RuntimeException("lifeSpan cannot be negative");
        }

        this.name = name;
        this.lifeSpan = lifeSpan;
        this.params = params;
    }

    public static ContextValue of(String name, int lifeSpan) {
        return new ContextValue(name, lifeSpan, null);
    }

    public static ContextValue of(String name, int lifeSpan, Map<String, String> params) {
        return new ContextValue(name, lifeSpan, params);
    }

    public static ContextValueBuilder builder(String name, int lifeSpan) {
        return new ContextValueBuilder(name, lifeSpan);
    }

    public static class ContextValueBuilder {
        private final String name;
        private final int lifeSpan;
        private Map<String, String> params;

        public ContextValueBuilder(String name, int lifeSpan) {
            this.name = name;
            this.lifeSpan = lifeSpan;
        }

        public ContextValueBuilder params(Map<String, String> params) {
            this.params = params;
            return this;
        }

        public ContextValueBuilder put(String key, String value) {
            if (params == null) {
                params = new HashMap<>();
            }
            params.put(key, value);
            return this;
        }

        public ContextValue build() {
            return new ContextValue(name, lifeSpan, params);
        }
    }
}
