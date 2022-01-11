package com.rest.pojo.assignment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Geo {
    private String lat;
    private String lng;

    public Geo(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public Geo() {

    }
}
