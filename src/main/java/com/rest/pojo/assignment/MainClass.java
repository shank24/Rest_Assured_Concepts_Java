package com.rest.pojo.assignment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainClass {

    Address address;
    private String name;
    private String username;
    private String email;
    private String id;

    public MainClass(Address address, String name, String username, String email) {
        this.address = address;
        this.name = name;
        this.username = username;
        this.email = email;
    }

    public MainClass() {

    }
}
