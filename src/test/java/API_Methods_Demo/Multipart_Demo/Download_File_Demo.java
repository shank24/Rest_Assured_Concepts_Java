package API_Methods_Demo.Multipart_Demo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;

import static io.restassured.RestAssured.given;


public class Download_File_Demo {

    @BeforeClass
    public void beforeClass() {

        /**
         * RequestSpec Builder
         */
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

        requestSpecBuilder.setBaseUri("https://raw.githubusercontent.com/");
        requestSpecBuilder.log(LogDetail.ALL);

        RestAssured.requestSpecification = requestSpecBuilder.build();

        /**
         * ResponseSpec Builder
         */
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder()
                .log(LogDetail.ALL);

        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void validate_Download_File() throws IOException {

        InputStream is = given()
                .when()
                .get("appium/appium/master/sample-code/apps/ApiDemos-debug.apk")
                .then()
                .log()
                .all()
                .extract()
                .response()
                .asInputStream();

        OutputStream outputStream = new FileOutputStream(new File("ApiDemos-debug.apk"));
        byte[] bytes = new byte[is.available()];
        is.read(bytes);
        outputStream.write(bytes);
        outputStream.close();
    }

}
