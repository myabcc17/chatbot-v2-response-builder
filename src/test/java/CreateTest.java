import com.github.myabcc17.SkillResponseV2Builder;
import com.github.myabcc17.template.ContextValue;
import com.github.myabcc17.template.common.Button;
import com.github.myabcc17.template.component.BasicCard;
import com.github.myabcc17.template.component.SimpleText;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CreateTest {
    SkillResponseV2Builder skillResponseV2Builder = new SkillResponseV2Builder();

    @Test
    void testSimpleText() {
        skillResponseV2Builder.addComponent(SimpleText.of("simpleText of"));
        skillResponseV2Builder.addComponent(SimpleText.builder("simpleText").build());
    }

    @Test
    void testExceedOutput() {
        skillResponseV2Builder.addComponent(SimpleText.of("a"));
        skillResponseV2Builder.addComponent(SimpleText.of("a"));
        skillResponseV2Builder.addComponent(SimpleText.of("a"));
        skillResponseV2Builder.addContextValue(ContextValue.builder("Score", 10)
                .put("score", "10")
                .build());
        System.out.println(skillResponseV2Builder.build());
    }

    @Test
    void test() {
        skillResponseV2Builder
                .addComponent(BasicCard.builder()
                        .description("fesf")
                        .buttons(List.of(
                                Button.builder("fef", Button.Action.WEBLINK)
                                .webLinkUrl("kakaotalk://bizplugin?plugin_id=60fwaefwae&product_id=pid")
                                .build()
                        ))
                        .build()
                )
                .build();
    }
}
