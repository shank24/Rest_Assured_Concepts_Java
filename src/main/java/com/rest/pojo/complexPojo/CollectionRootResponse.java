package com.rest.pojo.complexPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CollectionRootResponse extends CollectionRootBase {
    CollectionResponse collection;

    public CollectionRootResponse(CollectionResponse collection) {
        this.collection = collection;
    }

    public CollectionRootResponse() {
    }
}
