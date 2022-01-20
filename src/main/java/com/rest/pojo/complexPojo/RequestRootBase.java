package com.rest.pojo.complexPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestRootBase {
    private String name;

    public RequestRootBase(String name) {
        this.name = name;
    }

    public RequestRootBase() {
    }
}
