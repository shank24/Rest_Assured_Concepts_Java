package com.rest.pojo.complexPojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Collection {
    Info info;
    List<Folder> item;

    public Collection(Info info, List<Folder> item) {
        this.info = info;
        this.item = item;
    }

    public Collection() {

    }
}
