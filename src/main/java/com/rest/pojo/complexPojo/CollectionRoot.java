package com.rest.pojo.complexPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CollectionRoot {
    Collection collection;

    public CollectionRoot(Collection collection) {
        this.collection = collection;
    }

    public CollectionRoot() {
    }
}
