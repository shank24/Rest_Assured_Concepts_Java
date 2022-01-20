package com.rest.pojo.complexPojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class URL {

    private String raw;
    private String protocol;
    private List<String> host;
    private List<String> path;

    public URL() {

    }

    public URL(String raw, String protocol, List<String> host, List<String> path) {
        this.raw = raw;
        this.protocol = protocol;
        this.host = host;
        this.path = path;
    }


}
