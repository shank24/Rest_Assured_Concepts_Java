package com.rest.pojo.complexPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Body {
    private String mode;
    private String raw;

    public Body(String mode, String raw) {
        this.mode = mode;
        this.raw = raw;
    }

    public Body() {
    }
}
