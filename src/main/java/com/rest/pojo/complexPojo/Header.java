package com.rest.pojo.complexPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Header {
    private String key;
    private String value;

    public Header() {
    }

    public Header(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
