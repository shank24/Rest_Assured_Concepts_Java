package API_Methods_Demo.Payload_Demo;

import com.rest.endpoints.Endpoints_Web_Services;
import com.rest.propertyReader.ObjectReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;


public class Payload_File_Demo {

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
    public void validate_Post_Verb_Payload_From_File() {
        File file = new File("src/main/resources/json_Files/CreateWorkspace.json");
        given()
                .body(file)
                .when()
                .post(Endpoints_Web_Services.WORKSPACE)
                .then()
                .assertThat()
                .body("workspace.name", equalTo("My_Second_Workspace"),
                        "workspace.id", matchesPattern("^[a-z0-9-]{36}$"));

    }


    @Test
    public void validate_Post_Verb_Payload_From_Map() {

        HashMap<String, Object> mainObject = new HashMap<>();

        HashMap<String, String> nestedObject = new HashMap<>();

        nestedObject.put("name", "My_Third_Workspace");
        nestedObject.put("type", "personal");
        nestedObject.put("description", "Creating Via HashMap");

        mainObject.put("workspace", nestedObject);


        given()
                .body(mainObject)
                .when()
                .post(Endpoints_Web_Services.WORKSPACE)
                .then()
                .assertThat()
                .body("workspace.name", equalTo("My_Third_Workspace"),
                        "workspace.id", matchesPattern("^[a-z0-9-]{36}$"));

    }

}
