package com.github.myabcc17.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class SkillPayload {
    private static final Gson gson = new GsonBuilder().create();

    private UserRequestPayload userRequest;
    private IntentPayload intent;
    private ActionPayload action;
    private BotPayload bot;
    private List<ContextPayload> contexts;

    public String getUtterance() {
        if (userRequest != null && userRequest.getUtterance() != null) {
            return userRequest.getUtterance();
        }
        return null;
    }

    public ContextPayload getContextByName(String contextName) {
        return this.getContexts().stream()
                .filter(contextPayload -> contextPayload.getName().equals(contextName))
                .findFirst()
                .orElseGet(null);
    }

    public String getContextParamValueByNameAndKey(String contextName, String key) {
        return ((Map<String, String>)this.getContextByName(contextName).getParams().get(key)).get("value");
    }

    public String getActionParamByName(String paramName) {
        return this.getAction().getParams().get(paramName);
    }

    public static SkillPayload from(Map<String, Object> payload) {
        return gson.fromJson(gson.toJson(payload), SkillPayload.class);
    }

    public static SkillPayload from(String payload) {
        return gson.fromJson(payload, SkillPayload.class);
    }
 }
