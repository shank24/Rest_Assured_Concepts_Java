package API_Methods_Demo.Json_Schema;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class Json_Validator_Demo {

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
