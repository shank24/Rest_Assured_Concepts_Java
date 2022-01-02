package API_Methods_Demo.Json_Schema;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class Json_Validator_Demo {

    /*@BeforeClass
    public void beforeClass() {

        *//**
     * RequestSpec Builder
     *//*
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

        requestSpecBuilder.setBaseUri("https://postman-echo.com/");
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecBuilder.log(LogDetail.ALL);

        RestAssured.requestSpecification = requestSpecBuilder.build();

        */

    /**
     * ResponseSpec Builder
     *//*
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);

        RestAssured.responseSpecification = responseSpecBuilder.build();
    }*/
    @Test
    public void validate_Json_Schema_Validator() {

        given()
                .baseUri("https://postman-echo.com")
                .log()
                .all()
                .when()
                .get("/get")
                .then()
                .log()
                .all()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("json_Files/EchoGet.json"));
    }


}
