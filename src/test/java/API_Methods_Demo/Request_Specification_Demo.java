package API_Methods_Demo;

import com.rest.endpoints.Endpoints_Web_Services;
import com.rest.propertyReader.ObjectReader;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Request_Specification_Demo {

    @Test
    public void request_Spec_Demo() {
        RequestSpecification requestSpecification = given()
                .baseUri(ObjectReader.reader.getURI())
                .header("x-api-key", ObjectReader.reader.getKey());

        given().spec(requestSpecification)
                .when()
                .log().method()
                .get(Endpoints_Web_Services.WORKSPACE)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }
}
