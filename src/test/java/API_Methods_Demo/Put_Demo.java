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


public class Put_Demo {

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
                .extract().path("workspaces[0].id");

        System.out.println("Workspace Id " + workspaceId);

    }

    @Test
    public void validate_Put_Verb() {
        String payload = "{\n" +
                "    \"workspace\": {\n" +
                "        \"id\": \"9815f1bd-38be-4bce-8003-e68bc0f4a2d8\",\n" +
                "            \"name\": \"My_Workspace_New\",\n" +
                "            \"type\": \"personal\"\n" +
                "    }\n" +
                "}";

        given()
                .body(payload)
                .when()
                .put(Endpoints_Web_Services.WORKSPACE + "/" + workspaceId)
                .then()
                .assertThat()
                .body("workspace.name", equalTo("My_Workspace_New"),
                        "workspace.id", matchesPattern("^[a-z0-9-]{36}$"),
                        "workspace.id", equalTo(workspaceId));

    }


    @Test
    public void validate_Put_Verb_Path_Param() {
        String payload = "{\n" +
                "    \"workspace\": {\n" +
                "        \"id\": \"9815f1bd-38be-4bce-8003-e68bc0f4a2d8\",\n" +
                "            \"name\": \"My_Workspace_New\",\n" +
                "            \"type\": \"personal\"\n" +
                "    }\n" +
                "}";

        given()
                .pathParam("workspaceParam", workspaceId)
                .body(payload)
                .when()
                .put(Endpoints_Web_Services.WORKSPACE + "/{workspaceParam}")
                .then()
                .assertThat()
                .body("workspace.name", equalTo("My_Workspace_New"),
                        "workspace.id", matchesPattern("^[a-z0-9-]{36}$"),
                        "workspace.id", equalTo(workspaceId));

    }
}
