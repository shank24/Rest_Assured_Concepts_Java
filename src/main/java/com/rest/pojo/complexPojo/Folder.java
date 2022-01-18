package com.rest.pojo.complexPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Folder {
    private String name;
    List<RequestRoot> item;

    public Folder(String name, List<RequestRoot> item) {
        this.name = name;
        this.item = item;
    }

    public Folder() {

    }
}
