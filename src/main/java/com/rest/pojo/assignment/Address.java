package com.rest.pojo.assignment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    Geo geo;
    private String street;
    private String suite;
    private String city;
    private String zipcode;


    public Address(Geo geo, String street, String suite, String city, String zipcode) {
        this.geo = geo;
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
    }

    public Address() {

    }

}
