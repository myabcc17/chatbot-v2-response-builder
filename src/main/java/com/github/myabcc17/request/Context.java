package com.github.myabcc17.request;

import lombok.Getter;

import java.util.Map;

@Getter
public class Context {
    private String name;
    private Integer lifespan;
    private Map<String, Object> params;
}
