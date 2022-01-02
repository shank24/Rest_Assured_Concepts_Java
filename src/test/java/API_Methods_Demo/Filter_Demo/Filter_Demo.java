package API_Methods_Demo.Filter_Demo;

import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class Filter_Demo {


    @Test
    public void validate_Filter_Demo() {

        given()
                .baseUri("https://postman-echo.com")
                .filter(new RequestLoggingFilter(LogDetail.BODY))
                .filter(new ResponseLoggingFilter(LogDetail.STATUS))
                //.log().all()
                .when()
                .get("/get")
                .then()
                //.log().all()
                .assertThat()
                .statusCode(200);
    }


}
