package com.rest.pojo.complexPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class RequestBase {
    private String method;
    List<Header> header;
    Body body;
    private String description;

    public RequestBase(String method, List<Header> header, Body body, String description) {
        this.method = method;
        this.header = header;
        this.body = body;
        this.description = description;
    }

    public RequestBase() {
    }
}
