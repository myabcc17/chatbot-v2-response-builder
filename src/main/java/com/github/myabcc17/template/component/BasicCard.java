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
    private final Thumbnail thumbnail;
    private Profile profile;
    private Social social;
    private List<Button> buttons;

    private static final int MAX_DESCRIPTION = 230;
    private static final int MAX_BUTTON = 3;

    private BasicCard(String title, String description, Thumbnail thumbnail, Profile profile,
            Social social, List<Button> buttons) {
        super("basicCard");

        Objects.requireNonNull(thumbnail, "thumbnail은 null이 될 수 없습니다.");

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
    }

    public static BasicCard of(Thumbnail thumbnail) {
        return new BasicCard(null, null, thumbnail, null, null, null);
    }

    public static BasicCard of(String title, String description, Thumbnail thumbnail,
            List<Button> buttons) {
        return new BasicCard(title, description, thumbnail, null, null, buttons);
    }

    public static BasicCard of(String title, String description, Thumbnail thumbnail,
            Profile profile, Social social, List<Button> buttons) {
        return new BasicCard(title, description, thumbnail, profile, social, buttons);
    }

    public static BasicCardBuilder builder(Thumbnail thumbnail) {
        return new BasicCardBuilder(thumbnail);
    }

    public static class BasicCardBuilder {
        private String title;
        private String description;
        private final Thumbnail thumbnail;
        private Profile profile;
        private Social social;
        private List<Button> buttons;

        public BasicCardBuilder(Thumbnail thumbnail) {
            this.thumbnail = thumbnail;
        }

        public BasicCardBuilder title(String title) {
            this.title = title;
            return this;
        }

        public BasicCardBuilder description(String description) {
            this.description = description;
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

        public BasicCard build() {
            return new BasicCard(title, description, thumbnail, profile, social, buttons);
        }
    }
}
