package API_Methods_Demo.Pojo_Demo;

import com.rest.endpoints.Endpoints_Web_Services;
import com.rest.pojo.workspace.Workspace;
import com.rest.pojo.workspace.WorkspaceRoot;
import com.rest.propertyReader.ObjectReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;


public class Workspace_Pojo_Test {

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


    @Test(dataProvider = "workspace")
    public void validate_Workspace_Pojo(String name, String type, String description) {

        Workspace workspace = new Workspace(name, type, description);

        WorkspaceRoot workspaceRoot = new WorkspaceRoot(workspace);


        WorkspaceRoot deSerializedPojo = given()
                .body(workspaceRoot)
                .when()
                .post(Endpoints_Web_Services.WORKSPACE)
                .then()
                .extract()
                .response()
                .as(WorkspaceRoot.class);

        assertThat(deSerializedPojo.getWorkspace().getName(),
                equalTo(workspaceRoot.getWorkspace().getName()));
        assertThat(deSerializedPojo.getWorkspace().getId(),
                matchesPattern("^[a-z0-9-]{36}$"));

    }


    @DataProvider(name = "workspace")
    public Object[][] getWorkspace() {
        return new Object[][]
                {
                        {"My_WorkSpace_6", "personal", "description"},
                        {"My_WorkSpace_7", "team", "description"}
                };
    }

}
