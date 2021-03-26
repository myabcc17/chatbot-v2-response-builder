package com.github.myabcc17.request;

import lombok.Getter;

import java.util.Map;

@Getter
public class ActionPayload {
    String id;
    String name;
    Map<String, String> params;
    Map<String, Map<String, Object>> detailParams;
    Map<String, Object> clientExtra;
}
