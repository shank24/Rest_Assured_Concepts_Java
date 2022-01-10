package API_Methods_Demo.Pojo_Demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.pojo.simplePojo.PostMock;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class Simple_Pojo_Demo {

    @BeforeClass
    public void beforeClass() {

        /**
         * RequestSpec Builder
         */
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

        requestSpecBuilder.setBaseUri("https://7fd638e3-e495-4a96-9c53-2cafad55c9d5.mock.pstmn.io/");
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecBuilder.addHeader("x-mock-match-request-body", "true");
        requestSpecBuilder.setConfig(config.encoderConfig(EncoderConfig.encoderConfig().
                appendDefaultContentCharsetToContentTypeIfUndefined(false)));
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
    public void validate_Simple_Pojo() {

        PostMock postMock = new PostMock();
        postMock.setKey1("value1");
        postMock.setKey2("value2");

        given()
                .body(postMock)
                .when()
                .post("/postSimple")
                .then()
                .log()
                .all()
                .assertThat()
                .body("key1", equalTo(postMock.getKey1()),
                        "key2", equalTo(postMock.getKey2()));

    }

    @Test
    public void de_Serialize_Simple_Pojo() throws JsonProcessingException {

        PostMock postMockPojo = new PostMock();
        postMockPojo.setKey1("value1");
        postMockPojo.setKey2("value2");

        PostMock deSerializePojo = given()
                .body(postMockPojo)
                .when()
                .post("/postSimple")
                .then()
                .log()
                .all()
                .extract()
                .response()
                .as(PostMock.class);

        ObjectMapper objectMapper = new ObjectMapper();
        String deSerializeStr = objectMapper.writeValueAsString(deSerializePojo);
        String postMockStr = objectMapper.writeValueAsString(postMockPojo);

        MatcherAssert.assertThat(objectMapper.readTree(deSerializeStr),
                equalTo(objectMapper.readTree(postMockStr)));

    }

}
