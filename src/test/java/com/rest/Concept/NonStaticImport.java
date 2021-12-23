package com.rest.Concept;

import com.rest.propertyReader.ObjectReader;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class NonStaticImport {

    @Test
    public void simple_test_case(){

        RestAssured.given()
                .baseUri(ObjectReader.reader.getURI())
                .header("x-api-key", ObjectReader.reader.getKey())
           .when()
                .get("/workspaces")
           .then()
                .statusCode(200)
                .body("workspaces[0].name", Matchers.is(Matchers.equalTo("My Workspace"))
                ,"workspaces[0].type",Matchers.is(Matchers.equalTo("personal")));
    }
}
