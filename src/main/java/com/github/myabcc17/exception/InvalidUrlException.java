package com.github.myabcc17.exception;

public class InvalidUrlException extends RuntimeException {
    public InvalidUrlException() {
        super("URL 형식이 올바르지 않습니다.");
    }
}
