package com.github.myabcc17.template.component;

import java.util.List;
import java.util.Objects;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import com.github.myabcc17.template.common.CarouselHeader;

@Getter
public class Carousel extends Component {
    private final String type;
    private final List<CarouselItem> items;
    private CarouselHeader carouselHeader;

    private static final int MAX_ITEMS = 10;

    private Carousel(String type, List<CarouselItem> items, CarouselHeader carouselHeader) {
        super("carousel");

        Objects.requireNonNull(type);
        Objects.requireNonNull(items);

        if (!StringUtils.equalsAny(type, "basicCard", "commerceCard")) {
            throw new RuntimeException("type은 'basicCard', 'commerceCard'만 가능합니다.");
        }

        if (items.size() > MAX_ITEMS) {
            throw new RuntimeException(String.format("items 개수는 %d개를 초과할 수 없습니다.", MAX_ITEMS));
        }

        this.type = type;
        this.items = items;
        this.carouselHeader = carouselHeader;
    }

    public static Carousel of(String type, List<CarouselItem> items) {
        return new Carousel(type, items, null);
    }

    public static Carousel of(String type, List<CarouselItem> items, CarouselHeader carouselHeader) {
        return new Carousel(type, items, carouselHeader);
    }

    public static class CarouselBuilder {
        private final String type;
        private final List<CarouselItem> items;
        private CarouselHeader carouselHeader;

        public CarouselBuilder(String type, List<CarouselItem> items) {
            this.type = type;
            this.items = items;
        }

        public CarouselBuilder carouselHeader(CarouselHeader carouselHeader) {
            this.carouselHeader = carouselHeader;
            return this;
        }

        public Carousel build() {
            return new Carousel(type, items, carouselHeader);
        }
    }
}
