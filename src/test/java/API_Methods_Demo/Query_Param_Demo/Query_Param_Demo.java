package API_Methods_Demo.Query_Param_Demo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;


public class Query_Param_Demo {

    @BeforeClass
    public void beforeClass() {

        /**
         * RequestSpec Builder
         */
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

        requestSpecBuilder.setBaseUri("https://postman-echo.com/");
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecBuilder.log(LogDetail.ALL);

        RestAssured.requestSpecification = requestSpecBuilder.build();

        /**
         * ResponseSpec Builder
         */
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);

        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void validate_Single_Query_Param() {

        given()
                //Generic
                //.param("foo1", "bar1")
                //Specific to Query Param
                .queryParam("foo1", "bar1")
                .when()
                .get("/get")
                .then()
                .log()
                .all();
    }

    @Test
    public void validate_Multiple_Query_Param() {

        HashMap<String, String> queryParam = new HashMap<>();
        queryParam.put("foo1", "bar1");
        queryParam.put("foo2", "bar2");
        queryParam.put("foo3", "bar3");
        queryParam.put("foo4", "bar4");
        queryParam.put("foo5", "bar5");
        queryParam.put("foo6", "bar6");

        given()
                .queryParams(queryParam)
                .when()
                .get("/get")
                .then()
                .log()
                .all();
    }


    @Test
    public void validate_Multi_Value_Query_Param() {

        given()
                //Generic
                //.param("foo1", "bar1")
                //Specific to Query Param
                .queryParam("foo1", "bar1", "bar2", "bar3")
                .when()
                .get("/get")
                .then()
                .log()
                .all();
    }
}
