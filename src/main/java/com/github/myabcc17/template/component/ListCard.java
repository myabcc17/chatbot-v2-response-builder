package com.github.myabcc17.template.component;

import java.util.List;
import java.util.Objects;
import com.github.myabcc17.template.common.Button;

public class ListCard extends Component {
    private final ListItem header;
    private final List<ListItem> items;
    private List<Button> buttons;

    private static final int MAX_ITEMS = 5;
    private static final int MAX_BUTTONS = 2;

    private ListCard(ListItem header, List<ListItem> items, List<Button> buttons) {
        super("listCard");

        Objects.requireNonNull(header);
        Objects.requireNonNull(items);

        if (items.size() > MAX_ITEMS) {
            throw new RuntimeException(String.format("item개수는 %d개를 초과할 수 없습니다.", MAX_BUTTONS));
        }

        if (buttons != null && buttons.size() > 2) {
            throw new RuntimeException(String.format("buttons개수는 %d개를 초과할 수 없습니다.", MAX_BUTTONS));
        }

        this.header = header;
        this.items = items;
        this.buttons = buttons;
    }

    public static ListCard of(ListItem header, List<ListItem> items) {
        return new ListCard(header, items, null);
    }

    public static ListCard of(ListItem header, List<ListItem> items, List<Button> buttons) {
        return new ListCard(header, items, buttons);
    }

    public static ListCardBuilder builder(ListItem header, List<ListItem> items) {
        return new ListCardBuilder(header, items);
    }

    public static class ListCardBuilder {
        private final ListItem header;
        private final List<ListItem> items;
        private List<Button> buttons;

        public ListCardBuilder(ListItem header, List<ListItem> items) {
            this.header = header;
            this.items = items;
        }

        public ListCardBuilder buttons(List<Button> buttons) {
            this.buttons = buttons;
            return this;
        }

        public ListCard build() {
            return new ListCard(header, items, buttons);
        }
    }
}
