package com.rest.pojo.complexPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FolderResponse extends FolderBase {
    List<RequestRootResponse> item;

    public FolderResponse(String name, List<RequestRootResponse> item) {
        super(name);
        this.item = item;
    }

    public FolderResponse() {

    }
}
