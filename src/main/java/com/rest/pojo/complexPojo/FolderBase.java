package com.rest.pojo.complexPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class FolderBase {
    private String name;

    public FolderBase(String name) {
        this.name = name;
    }

    public FolderBase() {

    }
}
