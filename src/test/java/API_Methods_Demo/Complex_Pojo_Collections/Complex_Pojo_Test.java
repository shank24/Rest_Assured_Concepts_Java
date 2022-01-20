package API_Methods_Demo.Complex_Pojo_Collections;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.endpoints.Endpoints_Web_Services;
import com.rest.pojo.complexPojo.*;
import com.rest.propertyReader.ObjectReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.json.JSONException;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.ValueMatcher;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;


public class Complex_Pojo_Test {

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

        Header header = new Header("Content-Type", "application/json");
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        Body body = new Body("raw", "{\"data\": \"123\"}");

        RequestRequest request = new RequestRequest("https://postman-echo.com/post", "POST",
                headerList, body, "This is a sample POST RequestBase");

        RequestRootRequest requestRoot = new RequestRootRequest("Sample POST RequestBase", request);

        List<RequestRootRequest> requestItemList = new ArrayList<>();
        requestItemList.add(requestRoot);

        FolderRequest folderRequest = new FolderRequest("This is a folderBase", requestItemList);
        List<FolderRequest> folderList = new ArrayList<>();
        folderList.add(folderRequest);

        Info info = new Info("CollectionBase - Stranger Things Season 4", "This is just a sample collectionBase.",
                "https://schema.getpostman.com/json/collection/v2.1.0/collection.json");

        CollectionRequest collection = new CollectionRequest(info, folderList);

        CollectionRootRequest collectionRoot = new CollectionRootRequest(collection);

        given()
                .body(collectionRoot)
                .when()
                .post(Endpoints_Web_Services.COLLECTION)
                .then();

    }

    @Test
    public void validate_Post_Verb_De_Serialize() throws JsonProcessingException, JSONException {

        Header header = new Header("Content-Type", "application/json");
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        Body body = new Body("raw", "{\"data\": \"123\"}");

        RequestRequest request = new RequestRequest("https://postman-echo.com/post", "POST",
                headerList, body, "This is a sample POST RequestBase");


        RequestRootRequest requestRoot = new RequestRootRequest("Sample POST RequestBase", request);
        List<RequestRootRequest> requestItemList = new ArrayList<>();
        requestItemList.add(requestRoot);

        FolderRequest folderRequest = new FolderRequest("This is a folderBase", requestItemList);
        List<FolderRequest> folderList = new ArrayList<>();
        folderList.add(folderRequest);

        Info info = new Info("CollectionBase - Stranger Things Season 4", "This is just a sample collection.",
                "https://schema.getpostman.com/json/collection/v2.1.0/collection.json");

        CollectionRequest collection = new CollectionRequest(info, folderList);

        CollectionRootRequest collectionRoot = new CollectionRootRequest(collection);


        String collection_Uid = given()
                .body(collectionRoot)
                .when()
                .post(Endpoints_Web_Services.COLLECTION)
                .then()
                .extract()
                .response()
                .path("collection.uid");


        CollectionRootResponse deSerialized_collectionRootBase = given()
                .pathParam("collection_Uid", collection_Uid)
                .when()
                .get(Endpoints_Web_Services.COLLECTION + "/{collection_Uid}")
                .then()
                .extract()
                .response()
                .as(CollectionRootResponse.class);

        ObjectMapper objectMapper = new ObjectMapper();
        String collectionRootstr = objectMapper.writeValueAsString(collectionRoot);
        String deSerializedCollectionRootstr = objectMapper.writeValueAsString(deSerialized_collectionRootBase);

        JSONAssert.assertEquals(collectionRootstr, deSerializedCollectionRootstr,
                new CustomComparator(JSONCompareMode.STRICT_ORDER,
                        new Customization("collection.item[*].item[*].request.url",
                                new ValueMatcher<Object>() {
                                    @Override
                                    public boolean equal(Object o1, Object o2) {
                                        return true;
                                    }
                                })));


        List<String> URLRequestList = new ArrayList<>();
        List<String> URLResponseList = new ArrayList<>();

        for (RequestRootRequest requestRequest : requestItemList) {
            System.out.println("Url from Request Payload " + requestRequest.getRequest().getUrl());
            URLRequestList.add(requestRequest.getRequest().getUrl());
        }

        List<FolderResponse> folderResponseList = deSerialized_collectionRootBase.getCollection().getItem();
        for (FolderResponse folderResponse : folderResponseList) {
            List<RequestRootResponse> requestRootResponseList = folderResponse.getItem();
            for (RequestRootResponse requestRootResponse : requestRootResponseList) {
                URL url = requestRootResponse.getRequest().getUrl();
                System.out.println("Url from Request Payload " + url.getRaw());
                URLResponseList.add(url.getRaw());
            }
        }

        assertThat(URLResponseList, containsInAnyOrder(URLRequestList.toArray()));
    }

}


/*
*
{
  "collectionBase": {
    "info": {
      "name": "Sample CollectionBase - Stranger Things Episode 9",
      "description": "This is just a sample collectionBase.",
      "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
    },
    "item": [
      {
        "name": "This is a folder",
        "item": [
          {
            "name": "Sample POST RequestBase",
            "requestBase": {
              "url": "https://postman-echo.com/post",
              "method": "POST",
              "header": [
                {
                  "key": "Content-Type",
                  "value": "application/json"
                }
              ],
              "body": {
                "mode": "raw",
                "raw": "{\"data\": \"123\"}"
              },
              "description": "This is a sample POST RequestBase"
            }
          }
        ]
      },
      {
        "name": "Sample GET RequestBase",
        "requestBase": {
          "url": "https://postman-echo/get",
          "method": "GET",
          "description": "This is a sample GET RequestBase"
        }
      }
    ]
  }
}
* */