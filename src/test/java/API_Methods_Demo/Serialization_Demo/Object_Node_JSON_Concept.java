package API_Methods_Demo.Serialization_Demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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


public class Object_Node_JSON_Concept {

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
    public void serialize_Object_Node() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode nestedObjectNode = objectMapper.createObjectNode();

        nestedObjectNode.put("name", "Jackson_Workspace_Object_Node");
        nestedObjectNode.put("type", "personal");
        nestedObjectNode.put("description", "Created For Jack-son Object Node");

        ObjectNode mainObjectNode = objectMapper.createObjectNode();
        mainObjectNode.set("workspace", nestedObjectNode);


        //Already Serialized
        String mainObjectStr = objectMapper.writeValueAsString(mainObjectNode);

        given()
                .body(mainObjectStr)
                .when()
                .post(Endpoints_Web_Services.WORKSPACE)
                .then()
                .assertThat()
                .body("workspace.name", equalTo("Jackson_Workspace_Object_Node"),
                        "workspace.id", matchesPattern("^[a-z0-9-]{36}$"));

    }

}
