package com.github.myabcc17.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

@Getter
public class SkillPayload {
    private static final Gson gson = new GsonBuilder().create();

    private UserRequestPayload userRequest;
    private IntentPayload intent;
    private ActionPayload action;
    private BotPayload bot;
    private List<Context> contexts;

    public String getUtterance() {
        if (userRequest != null && userRequest.getUtterance() != null) {
            return userRequest.getUtterance();
        }
        return null;
    }

    public static SkillPayload from(Map<String, Object> payload) {
        return gson.fromJson(gson.toJson(payload), SkillPayload.class);
    }

    public static SkillPayload from(String payload) {
        return gson.fromJson(payload, SkillPayload.class);
    }
 }
