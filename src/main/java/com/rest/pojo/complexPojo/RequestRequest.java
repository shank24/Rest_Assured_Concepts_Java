package com.rest.pojo.complexPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestRequest extends RequestBase {
    private String url;

    public RequestRequest(String url, String method, List<Header> header, Body body, String description) {
        super(method, header, body, description);
        this.url = url;
    }

    public RequestRequest() {
    }
}
