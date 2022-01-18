package com.rest.pojo.complexPojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Request {
    private String url;
    private String method;
    List<Header> header;
    Body body;
    private String description;

    public Request(String url, String method, List<Header> header, Body body, String description) {
        this.url = url;
        this.method = method;
        this.header = header;
        this.body = body;
        this.description = description;
    }

    public Request() {
    }
}
