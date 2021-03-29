package com.github.myabcc17.template.component;

import com.github.myabcc17.annotation.Exclude;

public class Component {
    @Exclude
    private String name;

    public Component(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
