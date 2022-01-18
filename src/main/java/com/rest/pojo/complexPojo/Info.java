package com.rest.pojo.complexPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Info {
    private String name;
    private String description;
    private String schema;

    public Info(String name, String description, String schema) {
        this.name = name;
        this.description = description;
        this.schema = schema;
    }

    public Info() {

    }

}
