package com.rest.pojo.complexPojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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
