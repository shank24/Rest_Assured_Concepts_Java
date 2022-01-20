package com.rest.pojo.complexPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectionRequest extends CollectionBase {
    List<FolderRequest> item;

    public CollectionRequest(Info info, List<FolderRequest> item) {
        super(info);
        this.item = item;
    }

    public CollectionRequest() {

    }
}
