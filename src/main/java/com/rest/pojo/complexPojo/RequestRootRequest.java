package com.rest.pojo.complexPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestRootRequest extends RequestRootBase {
    RequestRequest request;

    public RequestRootRequest(String name, RequestRequest request) {
        super(name);
        this.request = request;
    }

    public RequestRootRequest() {
    }
}
