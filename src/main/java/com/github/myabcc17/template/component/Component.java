package com.github.myabcc17.template.component;

public class Component {
    private transient String name;

    public Component(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
