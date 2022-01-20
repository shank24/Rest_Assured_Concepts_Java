package com.rest.pojo.complexPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FolderRequest extends FolderBase {
    List<RequestRootRequest> item;

    public FolderRequest(String name, List<RequestRootRequest> item) {
        super(name);
        this.item = item;
    }

    public FolderRequest() {

    }
}
