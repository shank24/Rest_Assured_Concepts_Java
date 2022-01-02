package API_Methods_Demo.Payload_Demo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class Payload_Complex_Demo_Assignment {

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
    public void validate_Post_Verb_Payload_From_Complex_Json() {

        //RGB1
        List<Integer> rgba1ArrayList = new ArrayList<>();

        rgba1ArrayList.add(255);
        rgba1ArrayList.add(255);
        rgba1ArrayList.add(255);
        rgba1ArrayList.add(1);

        //CodeHashmap1
        HashMap<String, Object> codeHashMap1 = new HashMap<>();
        codeHashMap1.put("rgba", rgba1ArrayList);
        codeHashMap1.put("hex", "#000");

        //ColorHashMap1
        HashMap<String, Object> colorHashMap1 = new HashMap();
        colorHashMap1.put("color", "black");
        colorHashMap1.put("category", "hue");
        colorHashMap1.put("type", "primary");
        colorHashMap1.put("code", codeHashMap1);


        //RGB2
        List<Integer> rgba2ArrayList = new ArrayList<>();

        rgba2ArrayList.add(0);
        rgba2ArrayList.add(0);
        rgba2ArrayList.add(0);
        rgba2ArrayList.add(1);


        //CodeHashmap2
        HashMap<String, Object> codeHashMap2 = new HashMap<>();
        codeHashMap2.put("rgba", rgba2ArrayList);
        codeHashMap2.put("hex", "#FFF");

        //ColorHashMap1
        HashMap<String, Object> colorHashMap2 = new HashMap();
        colorHashMap2.put("color", "white");
        colorHashMap2.put("category", "value");
        colorHashMap2.put("code", codeHashMap2);

        List<HashMap<String, Object>> colorsArrayList = new ArrayList<>();
        colorsArrayList.add(colorHashMap1);
        colorsArrayList.add(colorHashMap2);

        HashMap<String, Object> mainHashMap = new HashMap<>();
        mainHashMap.put("colors", colorsArrayList);

        given()
                .body(mainHashMap)
                .when()
                .post("/postAssJson")
                .then()
                .log()
                .all()
                .assertThat()
                .body("message", equalTo("Bingo"));

    }

}

/*
 {
  "colors": [

    {
      "color": "black",
      "category": "hue",
      "type": "primary",
      "code": {
        "rgba": [
          255,
          255,
          255,
          1
        ],
        "hex": "#000"
      }
    }
    ,

    {
      "color": "white",
      "category": "value",
      "code": {
        "rgba": [
          0,
          0,
          0,
          1
        ],
        "hex": "#FFF"
      }
    }


  ]
}


 */
