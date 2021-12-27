package API_Methods_Demo.Assertion_Demo;

import com.rest.endpoints.Endpoints_Web_Services;
import com.rest.propertyReader.ObjectReader;
import org.testng.annotations.Test;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class Hamcrest_Demo {

    /**
     * @author - Shanky Kalra
     * @Date - Dec 24, 2021
     */


    /*
    containsInAnyOrder() - Check all elements are in a collection and in any order.
    contains() - Check all elements are in a collection and in a strict order.
    hasItems() - Check  all elements are in a collection, but not strictly.
    hasItem() - Check Single element in a collection.
    empty() - Check if collection is empty.
    hasSize() - Check size of a collection.
    everyItem(startsWith()) - Check if every item in a collection,
    starts with specified string.

    hasKey() -> Map -> Check if map has the specified key [value is not checked].
    hasValue() -> Map -> Check is map has atleast one key matching specified value.
    hasEntry() -> Maps -> Check if map has the specified key value pair.
    equalTo(Collections.EMPTY_MAP) -> Maps [Check if empty].
    allOf() - Matches if all matchers matches.
    anyOf() - Matches if any of the matchers matches.
    */
    @Test
    public void contains_Hamcrest_Demo() {
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
                .body("workspaces.name", contains("My Workspace", "Team Workspace", "Dummy_New_Workspace"));
    }

    @Test
    public void contains_In_Any_Order_Hamcrest_Demo() {
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
                .body("workspaces.name", containsInAnyOrder("Team Workspace", "Dummy_New_Workspace", "My Workspace"),
                        "workspaces.name", is(not(empty())),
                        "workspaces.name", hasSize(3),
                        //"workspaces.name", everyItem(startsWith("My")
                        "workspaces[0]", hasKey("id"),
                        "workspaces[1]", hasValue("Team Workspace"),
                        "workspaces[0]", hasEntry("type", "personal"),
                        "workspaces[0]", not(equalTo(Collections.EMPTY_MAP)),
                        "workspaces.name[0]", allOf(startsWith("My"), containsString("Workspace")));
    }

}
