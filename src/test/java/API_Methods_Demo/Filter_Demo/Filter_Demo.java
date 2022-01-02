package API_Methods_Demo.Filter_Demo;

import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

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


    @Test
    public void validate_Filter_Demo_Log_File() throws FileNotFoundException {

        PrintStream FileOutputStream = new PrintStream(new File("restAssured.log"));

        given()
                .baseUri("https://postman-echo.com")
                .filter(new RequestLoggingFilter(FileOutputStream))
                .filter(new ResponseLoggingFilter(FileOutputStream))
                .when()
                .get("/get")
                .then()
                .assertThat()
                .statusCode(200);
    }


}
