import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import template.QuickReply;
import template.component.Component;
import template.component.SimpleText;

public class SkillResponseV2Builder {
    private List<Component> components;
    private List<QuickReply> quickReplies;
    private List<String> contextValues;
    private Map<String, Object> data;

    private static final String FIELD_VERSION = "version";
    private static final String FIELD_VERSION_VALUE = "2.0";
    private static final String FIELD_TEMPLATE = "template";
    private static final String FIELD_OUTPUTS = "outputs";
    private static final String FIELD_QUICK_REPLIES = "quickReplies";
    private static final String FIELD_CONTEXT = "context";
    private static final String FIELD_CONTEXT_VALUE = "values";
    private static final String FIELD_DATA = "data";
    private static final String DEFAULT_MESSAGE = "표시할 메시지가 없습니다.";

    private static final int MAX_COMPONENT = 3;
    private static final int MAX_QUICKREPLY = 10;

    public SkillResponseV2Builder() { }

    public Map<String, Object> build() {
        Map<String, Object> response = new HashMap<>();
        response.put(FIELD_VERSION, FIELD_VERSION_VALUE);

        Map<String, Object> template = new HashMap<>();

        if (!CollectionUtils.isEmpty(components) && components.size() > MAX_COMPONENT) {
            throw new RuntimeException(String.format("최대 출력 아이템은 %d개입니다.", MAX_COMPONENT));
        } else {
            if (CollectionUtils.isEmpty(components)) {
                SimpleText defaultMessage = SimpleText.of(DEFAULT_MESSAGE);
                components = List.of(defaultMessage);
            }

            template.put(FIELD_OUTPUTS, components.stream()
                    .map(component -> Map.of(component.getName(), component))
                    .collect(Collectors.toList()));
        }

        if (quickReplies != null) {
            if (quickReplies.size() > 10) {
                throw new RuntimeException(String.format("최대 출력 바로가기는 %d개입니다.", MAX_QUICKREPLY));
            } else if (quickReplies.size() > 0) {
                template.put(FIELD_QUICK_REPLIES, quickReplies);
            }
        }

        response.put(FIELD_TEMPLATE, template);

        if (!CollectionUtils.isEmpty(contextValues)) {
            response.put(FIELD_CONTEXT, Map.of(FIELD_CONTEXT_VALUE, contextValues));
        }

        if (!MapUtils.isEmpty(data)) {
            response.put(FIELD_DATA, data);
        }


        return response;
    }

    public SkillResponseV2Builder addComponent(Component component) {
        if (this.components == null) {
            components = new ArrayList<>();
        }
        components.add(component);
        return this;
    }

    public SkillResponseV2Builder addQuickReply(QuickReply quickReply) {
        if (this.quickReplies == null) {
            quickReplies = new ArrayList<>();
        }
        quickReplies.add(quickReply);
        return this;
    }

   public SkillResponseV2Builder addContext(String context) {
       if (this.contextValues == null) {
           contextValues = new ArrayList<>();
       }
        contextValues.add(context);
        return this;
    }

    public SkillResponseV2Builder addData(String key, Object value) {
        if (this.data == null) {
            data = new HashMap<>();
        }
        this.data.put(key, value);
        return this;
    }
}
