package com.github.myabcc17.template.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Social {
    private Integer like;
    private Integer comment;
    private Integer share;
}
