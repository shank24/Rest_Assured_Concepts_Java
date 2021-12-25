package API_Methods_Demo;

import com.rest.endpoints.Endpoints_Web_Services;
import com.rest.propertyReader.ObjectReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class Request_Specification_Demo {
    RequestSpecification requestSpecification;

    @BeforeClass
    public void beforeClass() {

        /*requestSpecification = given()
                .baseUri(ObjectReader.reader.getURI())
                .header("x-api-key", ObjectReader.reader.getKey());*/

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(ObjectReader.reader.getURI());
        requestSpecBuilder.addHeader("x-api-key", ObjectReader.reader.getKey());

        requestSpecification = requestSpecBuilder.build();
    }

    @Test

    public void request_Spec_Demo() {

        given().spec(requestSpecification)
                .when()
                .log().method()
                .get(Endpoints_Web_Services.WORKSPACE)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void log_request_Body() {


        given().spec(requestSpecification)
                .when()
                .log().all()
                .get(Endpoints_Web_Services.WORKSPACE)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    //non-Bdd Style
    @Test
    public void assert_Status_Code() {

        Response response = given().spec(requestSpecification).get(Endpoints_Web_Services.WORKSPACE).then().log().all().extract().response();
        assertThat(response.statusCode(), is(equalTo(200)));
    }

    @Test
    public void assert_Body() {

        Response response = given().spec(requestSpecification).get(Endpoints_Web_Services.WORKSPACE).then().log().all().extract().response();
        assertThat(response.path("workspaces[0].name"), is(equalTo("My Workspace")));
    }

}
