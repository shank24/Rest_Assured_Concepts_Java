package com.rest.pojo.workspace;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Workspace {
    private String name;
    private String type;
    private String description;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int i;

    public Workspace(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public Workspace() {

    }


}
