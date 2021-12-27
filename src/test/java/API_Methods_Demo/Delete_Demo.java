package API_Methods_Demo;

import com.rest.endpoints.Endpoints_Web_Services;
import com.rest.propertyReader.ObjectReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;


public class Delete_Demo {

    String workspaceId;

    @BeforeClass
    public void beforeClass() {

        /**
         * RequestSpec Builder
         */
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

        requestSpecBuilder.setBaseUri(ObjectReader.reader.getURI());
        requestSpecBuilder.addHeader("x-api-key", ObjectReader.reader.getKey());
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
    public void get_Workspace_Id() {
        workspaceId = given()
                .when()
                .get(Endpoints_Web_Services.WORKSPACE)
                .then()
                .extract().path("workspaces[3].id");

        System.out.println("Workspace Id " + workspaceId);

    }

    @Test
    public void validate_Del_Verb() {


        given()
                .when()
                .delete(Endpoints_Web_Services.WORKSPACE + "/" + workspaceId)
                .then()
                .assertThat()
                .body(
                        "workspace.id", matchesPattern("^[a-z0-9-]{36}$"),
                        "workspace.id", equalTo(workspaceId));

    }
}
