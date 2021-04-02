package com.github.myabcc17.request;

import lombok.Getter;

import java.util.Map;

@Getter
public class ContextPayload {
    private String name;
    private Integer lifespan;
    private Map<String, Object> params;

    public String getParamByName(String name) {
        return ((Map<String, String>)this.params.get(name)).get("value");
    }
}
