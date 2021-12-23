package com.rest.Concept;

import com.rest.endpoints.Endpoints_Web_Services;
import com.rest.propertyReader.ObjectReader;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class StaticImport {

    @Test
    public void simple_test_case(){


        given()
                .baseUri(ObjectReader.reader.getURI())
                .header("x-api-key", ObjectReader.reader.getKey() )
           .when()
                .get(Endpoints_Web_Services.WORKSPACE)
           .then()
                .statusCode(200)
                .body("workspaces[0].name", is(equalTo("My Workspace"))
                ,"workspaces[0].type",is(equalTo("personal")));
    }
}
