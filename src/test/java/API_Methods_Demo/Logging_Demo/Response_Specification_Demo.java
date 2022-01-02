package API_Methods_Demo.Logging_Demo;

import com.rest.endpoints.Endpoints_Web_Services;
import com.rest.propertyReader.ObjectReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class Response_Specification_Demo {

    //RequestSpecification requestSpecification;

    //ResponseSpecification responseSpecification;

    @BeforeClass
    public void beforeClass() {

        /*requestSpecification = given()
                .baseUri(ObjectReader.reader.getURI())
                .header("x-api-key", ObjectReader.reader.getKey());*/

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(ObjectReader.reader.getURI());
        requestSpecBuilder.addHeader("x-api-key", ObjectReader.reader.getKey());
        requestSpecBuilder.log(LogDetail.ALL);

        //Default RequestSpecification
        //This static variable will retains its value,
        // so we can directly use get()
        RestAssured.requestSpecification = requestSpecBuilder.build();

        /*responseSpecification = RestAssured.expect()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .log().all();*/

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);

        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @Test

    public void response_Spec_Demo() {

        get(Endpoints_Web_Services.WORKSPACE);
    }

    @Test
    public void assert_response_Type() {

        Response response = get(Endpoints_Web_Services.WORKSPACE)
                .then()
                .extract().response();

        assertThat(response.path("workspaces[0].name"), is(equalTo("My Workspace")));
    }

}
