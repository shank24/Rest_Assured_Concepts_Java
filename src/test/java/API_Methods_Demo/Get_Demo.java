package API_Methods_Demo;

import com.rest.propertyReader.ObjectReader;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Get_Demo {

    @Test
    public void validate_Get_Verb(){
        given()
                .baseUri(ObjectReader.reader.getURI());
    }
}
