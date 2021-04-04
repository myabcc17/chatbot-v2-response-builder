package com.github.myabcc17.request;

import lombok.Getter;

import java.util.Map;
import java.util.Optional;

@Getter
public class ContextPayload {
    private String name;
    private Integer lifespan;
    private Map<String, Param> params;

    public Optional<String> getParamByName(String name) {
       return Optional.ofNullable(this.params.get(name))
               .map(Param::getValue);
    }

    @Getter
    public class Param {
        private String value;
        private String resolvedValue;
    }
}
