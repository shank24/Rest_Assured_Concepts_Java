package API_Methods_Demo.Complex_Pojo_Collections;

import com.rest.endpoints.Endpoints_Web_Services;
import com.rest.pojo.complexPojo.*;
import com.rest.propertyReader.ObjectReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;


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

        Request request = new Request("https://postman-echo.com/post", "POST",
                headerList, body, "This is a sample POST Request");

        RequestRoot requestRoot = new RequestRoot("Sample POST Request", request);

        List<RequestRoot> requestItemList = new ArrayList<>();
        requestItemList.add(requestRoot);

        Folder folder = new Folder("This is a folder", requestItemList);
        List<Object> folderList = new ArrayList<>();
        folderList.add(folder);

        Info info = new Info("Collection - Stranger Things Season 4", "This is just a sample collection.",
                "https://schema.getpostman.com/json/collection/v2.1.0/collection.json");

        Collection collection = new Collection(info, folderList);

        CollectionRoot collectionRoot = new CollectionRoot(collection);

        given()
                .body(collectionRoot)
                .when()
                .post(Endpoints_Web_Services.COLLECTION)
                .then();

    }

    @Test
    public void validate_Post_Verb_De_Serialize() {

        Header header = new Header("Content-Type", "application/json");
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        Body body = new Body("raw", "{\"data\": \"123\"}");

        Request request = new Request("https://postman-echo.com/post", "POST",
                headerList, body, "This is a sample POST Request");

        RequestRoot requestRoot = new RequestRoot("Sample POST Request", request);

        List<RequestRoot> requestItemList = new ArrayList<>();
        requestItemList.add(requestRoot);

        Folder folder = new Folder("This is a folder", requestItemList);
        List<Object> folderList = new ArrayList<>();
        folderList.add(folder);

        Info info = new Info("Collection - Stranger Things Season 4", "This is just a sample collection.",
                "https://schema.getpostman.com/json/collection/v2.1.0/collection.json");

        Collection collection = new Collection(info, folderList);

        CollectionRoot collectionRoot = new CollectionRoot(collection);

        String collection_Uid = given()
                .body(collectionRoot)
                .when()
                .post(Endpoints_Web_Services.COLLECTION)
                .then()
                .extract()
                .response()
                .path("collection.uid");


        CollectionRoot deSerialized_collection_uid = given()
                .pathParam("collection_Uid", collection_Uid)
                .when()
                .get(Endpoints_Web_Services.COLLECTION + "/{collection_Uid}")
                .then()
                .extract()
                .response()
                .as(CollectionRoot.class);
    }

}


/*
*
{
  "collection": {
    "info": {
      "name": "Sample Collection - Stranger Things Episode 9",
      "description": "This is just a sample collection.",
      "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
    },
    "item": [
      {
        "name": "This is a folder",
        "item": [
          {
            "name": "Sample POST Request",
            "request": {
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
              "description": "This is a sample POST Request"
            }
          }
        ]
      },
      {
        "name": "Sample GET Request",
        "request": {
          "url": "https://postman-echo/get",
          "method": "GET",
          "description": "This is a sample GET Request"
        }
      }
    ]
  }
}
* */