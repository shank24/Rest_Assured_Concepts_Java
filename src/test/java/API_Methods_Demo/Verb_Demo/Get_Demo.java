package API_Methods_Demo.Verb_Demo;

import com.rest.endpoints.Endpoints_Web_Services;
import com.rest.propertyReader.ObjectReader;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class Get_Demo {

    @Test
    public void validate_Get_Verb() {
        given()
                .baseUri(ObjectReader.reader.getURI())
                .header("x-api-key", ObjectReader.reader.getKey())
                .when()
                .log().method()
                .get(Endpoints_Web_Services.WORKSPACE)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void validate_Response_Body() {
        given()
                .baseUri(ObjectReader.reader.getURI())
                .header("x-api-key", ObjectReader.reader.getKey())
                .when()
                .log().method()
                .get(Endpoints_Web_Services.WORKSPACE)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("workspaces.name", hasItems("My Workspace", "Team Workspace", "Dummy_New_Workspace"),
                        "workspaces.type", hasItems("personal", "team", "team"),
                        "workspaces[0].name", equalTo("My Workspace"),
                        "workspaces[0].name", is(equalTo("My Workspace")),
                        "workspaces.size()", equalTo(3),
                        "workspaces.name", hasItem("My Workspace"));

    }

    @Test
    public void extract_Response() {
        Response response = given()
                .baseUri(ObjectReader.reader.getURI())
                .header("x-api-key", ObjectReader.reader.getKey())
                .when()
                .log().method()
                .get(Endpoints_Web_Services.WORKSPACE)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.asPrettyString());
    }

    @Test
    public void extract_Single_Value() {
        Response response = given()
                .baseUri(ObjectReader.reader.getURI())
                .header("x-api-key", ObjectReader.reader.getKey())
                .when()
                .log().method()
                .get(Endpoints_Web_Services.WORKSPACE)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        JsonPath jsonPath = new JsonPath(response.asString());
        System.out.println("<-- Workspace Name --> " + jsonPath.get("workspaces[0].name"));
        System.out.println("<-- Workspace Name --> " + response.path("workspaces[0].name"));
    }

    @Test
    public void extract_Single_Value_Json_Path_Way() {
        String response = given()
                .baseUri(ObjectReader.reader.getURI())
                .header("x-api-key", ObjectReader.reader.getKey())
                .when()
                .log().method()
                .get(Endpoints_Web_Services.WORKSPACE)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response().asString();

        System.out.println("<-- Workspace Name --> " + JsonPath.from(response).get("workspaces[0].name"));
    }

    @Test
    public void hamcrest_Assert_On_Extracted_Response() {
        String name = given()
                .baseUri(ObjectReader.reader.getURI())
                .header("x-api-key", ObjectReader.reader.getKey())
                .when()
                .log().method()
                .get(Endpoints_Web_Services.WORKSPACE)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response().path("workspaces[0].name");

        //Hamcrest
        assertThat(name, equalTo("My Workspace"));

        //Test Ng
        Assert.assertEquals(name, "My Workspace");
        System.out.println("<-- Workspace Name --> " + name);
    }
}
