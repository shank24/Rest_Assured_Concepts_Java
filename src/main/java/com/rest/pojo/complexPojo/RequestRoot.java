package com.rest.pojo.complexPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestRoot {
    private String name;
    Request request;

    public RequestRoot(String name, Request request) {
        this.name = name;
        this.request = request;
    }

    public RequestRoot() {
    }
}
