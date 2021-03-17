package template.common;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class Social {
    private Integer like;
    private Integer comment;
    private Integer share;
}
