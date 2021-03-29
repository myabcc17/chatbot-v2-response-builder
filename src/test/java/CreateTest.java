import com.github.myabcc17.SkillResponseV2Builder;
import com.github.myabcc17.request.SkillPayload;
import com.github.myabcc17.template.ContextValue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import com.github.myabcc17.template.component.SimpleImage;
import com.github.myabcc17.template.component.SimpleText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

public class CreateTest {
    SkillResponseV2Builder skillResponseV2Builder = new SkillResponseV2Builder();
/*
    @AfterEach
    void print() {
        System.out.println("=============================================");
        System.out.println(gson.toJson(skillResponseV2Builder.build()));
        System.out.println("=============================================");
    }*/

    @Test
    void testSimpleText() {
        skillResponseV2Builder.addComponent(SimpleText.of("simpleText of"));
        skillResponseV2Builder.addComponent(SimpleText.builder("simpleText").build());
    }

    @Test
    void testSimpleImage() {
        skillResponseV2Builder.addComponent(SimpleImage.of("awef@awfew", "표시할 수 없음"));
        skillResponseV2Builder.addComponent(SimpleImage.builder("awef@awfew", "대체 문구").build());
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
}
