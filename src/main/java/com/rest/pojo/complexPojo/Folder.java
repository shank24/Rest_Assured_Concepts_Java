package com.rest.pojo.complexPojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Folder {
    private String name;
    List<RequestRoot> requestRoot;

    public Folder(String name, List<RequestRoot> requestRoot) {
        this.name = name;
        this.requestRoot = requestRoot;
    }

    public Folder() {

    }
}
