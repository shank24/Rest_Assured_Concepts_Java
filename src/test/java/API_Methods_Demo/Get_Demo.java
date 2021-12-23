package API_Methods_Demo;

import com.rest.endpoints.Endpoints_Web_Services;
import com.rest.propertyReader.ObjectReader;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Get_Demo {

    @Test
    public void validate_Get_Verb(){
        given()
                .baseUri(ObjectReader.reader.getURI())
                .header("x-api-key",ObjectReader.reader.getKey())
        .when()
                .get(Endpoints_Web_Services.WORKSPACE);
    }
}
