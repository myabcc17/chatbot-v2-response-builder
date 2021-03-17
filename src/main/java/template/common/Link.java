package template.common;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor(staticName = "of")
public class Link {
    private String pc;
    private String mobile;
    private String web;
}
