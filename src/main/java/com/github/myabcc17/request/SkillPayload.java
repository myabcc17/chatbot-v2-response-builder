package com.github.myabcc17.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Getter
public class SkillPayload {
    private static final Gson gson = new GsonBuilder().create();

    private UserRequestPayload userRequest;
    private IntentPayload intent;
    private ActionPayload action;
    private BotPayload bot;
    private List<ContextPayload> contexts;

    public Optional<String> getUtterance() {
        return Optional.ofNullable(userRequest)
                .map(UserRequestPayload::getUtterance);
    }

    public Optional<ContextPayload> getContextByName(String contextName) {
        return this.getContexts().stream()
                .filter(contextPayload -> contextPayload.getName().equals(contextName))
                .findFirst();
    }

    public Optional<String> getActionParamByName(String paramName) {
        return Optional.ofNullable(this.getAction().getParams())
                .map(m -> m.get(paramName));
    }

    public static SkillPayload from(Map<String, Object> payload) {
        return gson.fromJson(gson.toJson(payload), SkillPayload.class);
    }

    public static SkillPayload from(String payload) {
        return gson.fromJson(payload, SkillPayload.class);
    }
 }
