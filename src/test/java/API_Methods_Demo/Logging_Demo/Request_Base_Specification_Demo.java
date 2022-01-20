package API_Methods_Demo.Logging_Demo;

import com.rest.endpoints.Endpoints_Web_Services;
import com.rest.propertyReader.ObjectReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class Request_Base_Specification_Demo {

    //RequestSpecification requestSpecification;

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
    }

    @Test

    public void request_Spec_Demo() {

        get(Endpoints_Web_Services.WORKSPACE)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void log_request_Body() {


        get(Endpoints_Web_Services.WORKSPACE)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    //non-Bdd Style
    @Test
    public void assert_Status_Code() {

        Response response = get(Endpoints_Web_Services.WORKSPACE).then().log().all().extract().response();
        assertThat(response.statusCode(), is(equalTo(200)));
    }

    @Test
    public void assert_Body() {

        Response response = get(Endpoints_Web_Services.WORKSPACE).then().log().all().extract().response();
        assertThat(response.path("workspaces[0].name"), is(equalTo("My Workspace")));
    }

    @Test
    public void query_Test() {
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(RestAssured.requestSpecification);
        System.out.println(queryableRequestSpecification.getBaseUri());
        System.out.println(queryableRequestSpecification.getHeaders());

    }

}
