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


public class Post_Demo {

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
    public void validate_Post_Verb() {
        String payload = "{\n" +
                "    \"workspace\": \n" +
                "        {\n" +
                "            \"id\": \"9815f1bd-38be-4bce-8003-e68bc0f4a2d8\",\n" +
                "            \"name\": \"My Workspace3\",\n" +
                "            \"type\": \"personal\",\n" +
                "            \"description\" : \"This is dummy workspaces\"\n" +
                "        }\n" +
                "}";

        given()
                .body(payload)
                .when()
                .post(Endpoints_Web_Services.WORKSPACE)
                .then()
                .assertThat()
                .body("workspace.name", equalTo("My Workspace3"),
                        "workspace.id", matchesPattern("^[a-z0-9-]{36}$"));

    }

}
