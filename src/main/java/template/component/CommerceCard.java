package template.component;

import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import template.common.Button;
import template.common.Profile;
import template.common.Thumbnail;
import utils.StringValidator;

public class CommerceCard extends Component implements CarouselItem {
    private final String description;
    private final int price;
    private final String currency;
    private Integer discount;
    private Integer discountRate;
    private Integer discountedPrice;
    private final List<Thumbnail> thumbnails;
    private Profile profile;
    private final List<Button> buttons;

    private static final int MAX_DESCRIPTION = 230;

    private CommerceCard(String description, int price, String currency, Integer discount,
            Integer discountRate, Integer discountedPrice,
            List<Thumbnail> thumbnails, Profile profile, List<Button> buttons) {
        super("commerceCard");

        Objects.requireNonNull(description);
        Objects.requireNonNull(price);
        Objects.requireNonNull(currency);
        Objects.requireNonNull(thumbnails);
        Objects.requireNonNull(buttons);

        StringValidator.validateLength(description, MAX_DESCRIPTION);

        if (!StringUtils.equals(currency, "won")) {
            throw new RuntimeException("currency는 현재 'won'만 가능합니다.");
        }

        if (thumbnails.size() != 1) {
            throw new RuntimeException("thumbnail은 1개여야 합니다.");
        }

        if (buttons.size() > 3 || buttons.size() < 1) {
            throw new RuntimeException("buttons은 1개 이상 3개 이하여야 합니다.");
        }

        this.description = description;
        this.price = price;
        this.currency = currency;
        this.discount = discount;
        this.discountRate = discountRate;
        this.discountedPrice = discountedPrice;
        this.thumbnails = thumbnails;
        this.profile = profile;
        this.buttons = buttons;
    }

    public static CommerceCard of(String description, int price, String currency,
            List<Thumbnail> thumbnails, Profile profile, List<Button> buttons) {
        return new CommerceCard(description, price, currency, null, null, null, thumbnails, profile,
                buttons);
    }

    public static CommerceCardBuilder builder(String description, int price, String currency,
            List<Thumbnail> thumbnails, List<Button> buttons) {
        return new CommerceCardBuilder(description, price, currency, thumbnails, buttons);
    }

    public static class CommerceCardBuilder {
        private final String description;
        private final int price;
        private final String currency;
        private Integer discount;
        private Integer discountRate;
        private Integer discountedPrice;
        private final List<Thumbnail> thumbnails;
        private Profile profile;
        private final List<Button> buttons;

        public CommerceCardBuilder(String description, int price, String currency,
                List<Thumbnail> thumbnails, List<Button> buttons) {
            this.description = description;
            this.price = price;
            this.currency = currency;
            this.thumbnails = thumbnails;
            this.buttons = buttons;
        }

        public CommerceCardBuilder discount(Integer discount) {
            this.discount = discount;
            return this;
        }

        public CommerceCardBuilder discountRate(Integer discountRate) {
            this.discountRate = discountRate;
            return this;
        }

        public CommerceCardBuilder discountedPrice(Integer discountedPrice) {
            this.discountedPrice = discountedPrice;
            return this;
        }

        public CommerceCardBuilder profile(Profile profile) {
            this.profile = profile;
            return this;
        }

        public CommerceCard build() {
            return new CommerceCard(description, price, currency, discount, discountRate,
                    discountedPrice, thumbnails, profile, buttons);
        }
    }
}
