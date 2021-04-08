package com.github.myabcc17.template.component;

import java.util.List;
import java.util.Objects;
import com.github.myabcc17.template.common.Button;
import com.github.myabcc17.template.common.Profile;
import com.github.myabcc17.template.common.Social;
import com.github.myabcc17.template.common.Thumbnail;
import lombok.Getter;

@Getter
public class BasicCard extends Component implements CarouselItem {
    private String title;
    private String description;
    private Thumbnail thumbnail;
    private Profile profile;
    private Social social;
    private List<Button> buttons;
    private Boolean forwardable;

    private static final int MAX_DESCRIPTION = 230;
    private static final int MAX_BUTTON = 3;

    private BasicCard(String title, String description, Thumbnail thumbnail, Profile profile,
            Social social, List<Button> buttons, Boolean forwardable) {
        super("basicCard");

        /**
         * v1 card.text -> v2 basicCard: thumbnail 없어도 됨 (V2 응답 도움말에 없는 내용)
         * v1 card.image -> v2 basicCard: thumbnail 있어야함
         */
        if (thumbnail == null) {
            Objects.requireNonNull(description);
        }

        if (description != null && description.length() > MAX_DESCRIPTION) {
            throw new RuntimeException(String.format("description은 %d자를 넘을 수 없습니다.", MAX_DESCRIPTION));
        }

        if (buttons != null && buttons.size() > MAX_BUTTON) {
            throw new RuntimeException(String.format("button의 개수는 %d개를 넘을 수 없습니다.", MAX_BUTTON));
        }

        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.profile = profile;
        this.social = social;
        this.buttons = buttons;
        this.forwardable = forwardable;
    }

    public static BasicCard of(Thumbnail thumbnail) {
        return new BasicCard(null, null, thumbnail, null, null, null, false);
    }

    public static BasicCard of(String description, List<Button> buttons) {
        return new BasicCard(null, description, null, null, null, buttons, false);
    }

    public static BasicCard of(String title, String description, Thumbnail thumbnail,
            Profile profile, Social social, List<Button> buttons, Boolean forwardable) {
        return new BasicCard(title, description, thumbnail, profile, social, buttons, forwardable);
    }

    public static BasicCardBuilder builder() {
        return new BasicCardBuilder();
    }

    public static class BasicCardBuilder {
        private String title;
        private String description;
        private Thumbnail thumbnail;
        private Profile profile;
        private Social social;
        private List<Button> buttons;
        private Boolean forwardable;

        public BasicCardBuilder() { }

        public BasicCardBuilder title(String title) {
            this.title = title;
            return this;
        }

        public BasicCardBuilder description(String description) {
            this.description = description;
            return this;
        }

        public BasicCardBuilder thumbnail(Thumbnail thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        public BasicCardBuilder profile(Profile profile) {
            this.profile = profile;
            return this;
        }

        public BasicCardBuilder social(Social social) {
            this.social = social;
            return this;
        }

        public BasicCardBuilder buttons(List<Button> buttons) {
            this.buttons = buttons;
            return this;
        }

        public BasicCardBuilder forwardable(boolean forwardable) {
            this.forwardable = forwardable;
            return this;
        }

        public BasicCard build() {
            return new BasicCard(title, description, thumbnail, profile, social, buttons, forwardable);
        }
    }
}
