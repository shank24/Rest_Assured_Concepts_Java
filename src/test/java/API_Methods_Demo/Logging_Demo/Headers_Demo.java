package API_Methods_Demo.Logging_Demo;

import com.rest.endpoints.Endpoints_Web_Services;
import com.rest.propertyReader.ObjectReader;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class Headers_Demo {

    @Test
    public void assert_Response_Headers() {
        given()
                .baseUri(ObjectReader.reader.getURI())
                .header("x-api-key", ObjectReader.reader.getKey())
                .when()
                .log().method()
                .get(Endpoints_Web_Services.WORKSPACE)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .headers("X-RateLimit-Limit", "300",
                        "Server", "nginx");
    }

    @Test
    public void extract_Response_Headers() {
        Headers extracted_Headers = given()
                .baseUri(ObjectReader.reader.getURI())
                .header("x-api-key", ObjectReader.reader.getKey())
                .when()
                .log().method()
                .get(Endpoints_Web_Services.WORKSPACE)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .headers();

        System.out.println("Extracted Headers Name --> " + extracted_Headers.get("Server").getName());
        System.out.println("Extracted Headers Value --> " + extracted_Headers.get("Server").getValue());
        System.out.println("Extracted Headers Value --> " + extracted_Headers.getValue("Server"));

    }


    @Test
    public void extract_Multiple_Response_Headers() {
        Headers extracted_Headers = given()
                .baseUri(ObjectReader.reader.getURI())
                .header("x-api-key", ObjectReader.reader.getKey())
                .when()
                .log().method()
                .get(Endpoints_Web_Services.WORKSPACE)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .headers();

        for (Header header : extracted_Headers) {
            System.out.print("Header Name -->" + header.getName() + " , ");
            System.out.println("Header Value --> " + header.getValue());
        }


    }

}