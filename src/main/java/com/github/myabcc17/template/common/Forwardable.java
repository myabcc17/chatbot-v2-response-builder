package com.github.myabcc17.template.common;

public class Forwardable {
    private boolean forwardable;

    private Forwardable(boolean forwardable) {
        this.forwardable = forwardable;
    }

    public static Forwardable of(boolean forwardable) {
        return new Forwardable(forwardable);
    }
}
