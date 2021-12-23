package com.rest;

import com.rest.propertyReader.ObjectReader;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class StaticImport {

    @Test
    public void simple_test_case(){


        given()
                .baseUri("https://api.postman.com")
                .header("x-api-key", ObjectReader.reader.getKey() )
           .when()
                .get("/workspaces")
           .then()
                .statusCode(200)
                .body("workspaces[0].name", is(equalTo("My Workspace"))
                ,"workspaces[0].type",is(equalTo("personal")));
    }
}
