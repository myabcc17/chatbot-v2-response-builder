package com.github.myabcc17.utils;

public class StringValidator {
    public static void validateLength(String str, int length) {
        if (str.length() > length) {
            throw new RuntimeException(String.format("%s은 %d자를 초과할 수 없습니다.", str, length));
        }
    }
}
