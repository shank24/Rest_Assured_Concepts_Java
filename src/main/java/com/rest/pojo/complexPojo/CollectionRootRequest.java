package com.rest.pojo.complexPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CollectionRootRequest extends CollectionRootBase {
    CollectionRequest collection;

    public CollectionRootRequest(CollectionRequest collection) {
        this.collection = collection;
    }

    public CollectionRootRequest() {
    }
}
