package com.rest.pojo.complexPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CollectionBase {
    Info info;

    public CollectionBase(Info info) {
        this.info = info;
    }

    public CollectionBase() {

    }
}
