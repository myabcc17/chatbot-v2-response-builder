package com.github.myabcc17.utils;

import org.apache.commons.validator.routines.UrlValidator;

public class UrlUtils {
    private static UrlValidator urlValidator = new UrlValidator();

    public static boolean isValidUrl(String url) {
        return urlValidator.isValid(url);
    }
}
