package com.github.myabcc17.template.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(staticName = "of")
public class Link {
    private String pc;
    private String mobile;
    private String web;
}
