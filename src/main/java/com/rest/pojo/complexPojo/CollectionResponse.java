package com.rest.pojo.complexPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectionResponse extends CollectionBase {
    List<FolderResponse> item;

    public CollectionResponse(Info info, List<FolderResponse> item) {
        super(info);
        this.item = item;
    }

    public CollectionResponse() {

    }
}
