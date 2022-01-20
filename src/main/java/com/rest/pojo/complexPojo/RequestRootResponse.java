package com.rest.pojo.complexPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestRootResponse extends RequestRootBase {
    RequestResponse request;

    public RequestRootResponse(String name, RequestResponse request) {
        super(name);
        this.request = request;
    }

    public RequestRootResponse() {
    }
}
