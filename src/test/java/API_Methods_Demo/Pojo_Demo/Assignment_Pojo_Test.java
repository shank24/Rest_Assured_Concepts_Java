package API_Methods_Demo.Pojo_Demo;

import com.rest.pojo.assignment.Address;
import com.rest.pojo.assignment.Geo;
import com.rest.pojo.assignment.MainClass;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.matchesPattern;


public class Assignment_Pojo_Test {

    @BeforeClass
    public void beforeClass() {

        /**
         * RequestSpec Builder
         */
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

        requestSpecBuilder.setBaseUri("https://jsonplaceholder.typicode.com/");
        //requestSpecBuilder.addHeader("x-api-key", ObjectReader.reader.getKey());
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecBuilder.log(LogDetail.ALL);

        RestAssured.requestSpecification = requestSpecBuilder.build();

        /**
         * ResponseSpec Builder
         */
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);

        RestAssured.responseSpecification = responseSpecBuilder.build();
    }


    @Test
    public void validate_Ass_Pojo() {

        Geo geo = new Geo("-37.3159", "81.1496");
        Address address = new Address(geo, "Kulas Light",
                "Apt. 556", "Gwenborough", "92998-3874");
        MainClass mainClass = new MainClass(address, "Leanne Graham"
                , "Bret", "Sincere@april.biz");


        MainClass deSerializedPojo = given()
                .body(mainClass)
                .when()
                .post("/users")
                .then()
                .extract()
                .response()
                .as(MainClass.class);

        assertThat(deSerializedPojo.getId(),
                matchesPattern("^[0-9]*$"));
    }


}
