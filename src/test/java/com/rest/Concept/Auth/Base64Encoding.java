package com.rest.Concept.Auth;

import java.util.Base64;

public class Base64Encoding {
    public static void main(String[] args) {

        String uname_pass = "myusername:password";

        String base64Encoded = Base64.getEncoder().encodeToString(uname_pass.getBytes());
        System.out.println("Encoded " + base64Encoded);

        byte[] decodedBytes = Base64.getDecoder().decode(base64Encoded);
        System.out.println("Decoded " + new String(decodedBytes));
    }
}
