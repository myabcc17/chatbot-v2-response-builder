package com.github.myabcc17.request;

import lombok.Getter;

import java.util.Map;

@Getter
public class UserRequestPayload {
    Block block;
    User user;
    String utterance;
    String lang;
    Map<String, Object> params;

    @Getter
    class Block {
        String id;
        String name;
    }

    @Getter
    class User {
        String id;
        String type;
        Properties properties;

        @Getter
        class Properties {
            String botUserKey;
            String plusFriendUserKey;
            String appUserId;
        }
    }
}
