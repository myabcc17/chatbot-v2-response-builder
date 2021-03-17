package template.common;

import java.util.Objects;

public class Profile {
    private final String nickname;
    private String imageUrl;

    private Profile(String nickname, String imageUrl) {
        Objects.requireNonNull(nickname);

        this.nickname = nickname;
        this.imageUrl = imageUrl;
    }

    public static Profile of(String nickname) {
        return new Profile(nickname, null);
    }

    public static Profile of(String nickname, String imageUrl) {
        return new Profile(nickname, imageUrl);
    }

    public static ProfileBuilder builder(String nickname) {
        return new ProfileBuilder(nickname);
    }

    public static class ProfileBuilder {
        private final String nickname;
        private String imageUrl;

        public ProfileBuilder(String nickname) {
            this.nickname = nickname;
        }

        public ProfileBuilder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Profile build() {
            return new Profile(nickname, imageUrl);
        }
    }

}
