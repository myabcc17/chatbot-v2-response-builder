package com.github.myabcc17.request;

import lombok.Getter;

import java.util.Map;

@Getter
public class ContextPayload {
    private String name;
    private Integer lifespan;
    private Map<String, Param> params;

    public String getParamByName(String name) {
       return this.params.get(name).getValue();
    }

    @Getter
    public class Param {
        private String value;
        private String resolvedValue;
    }
}
