package com.rest.pojo.workspace;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Workspace {
    private String name;
    private String type;
    private String description;
    private String id;

    public Workspace(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public Workspace() {

    }


}
