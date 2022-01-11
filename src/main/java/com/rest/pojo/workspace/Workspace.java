package com.rest.pojo.workspace;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@JsonIgnoreProperties(value = "id", allowSetters = true)

/*
If we want to use property during
Serialization - allowgetters = true
De-Serialization - allowsetters = true
*/


public class Workspace {
    private String name;
    private String type;
    private String description;

    //@JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    //@JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonIgnore
    private int i;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private HashMap<String, String> myHashMap;

    public Workspace(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public Workspace() {

    }


}
