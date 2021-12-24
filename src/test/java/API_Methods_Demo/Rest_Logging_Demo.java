package API_Methods_Demo;

import com.rest.endpoints.Endpoints_Web_Services;
import com.rest.propertyReader.ObjectReader;
import io.restassured.config.LogConfig;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class Rest_Logging_Demo {
    /**
     * @author - Shanky Kalra
     * @Date - Dec 25, 2021
     */

    @Test
    public void request_Response_Logging() {
        given()
                .baseUri(ObjectReader.reader.getURI())
                .header("x-api-key", ObjectReader.reader.getKey())
                .when()
                .log().headers()
                .get(Endpoints_Web_Services.WORKSPACE)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void log_If_Error() {
        given()
                .baseUri(ObjectReader.reader.getURI())
                .header("x-api-key", ObjectReader.reader.getKey())
                .when()
                .log().all()
                .get(Endpoints_Web_Services.WORKSPACE)
                .then()
                .log().ifError()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void log_If_Validation_Fails() {
        given()
                .baseUri(ObjectReader.reader.getURI())
                .header("x-api-key", ObjectReader.reader.getKey())
                .config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
                .when()
                //.log().ifValidationFails()
                .get(Endpoints_Web_Services.WORKSPACE)
                .then()
                //.log().ifValidationFails()
                .assertThat()
                .statusCode(201);
    }

    //Alternate to enable Log_If_Validation_Fails
    @Test
    public void log_If_Validation_Fails_Config() {
        given()
                .baseUri(ObjectReader.reader.getURI())
                .header("x-api-key", ObjectReader.reader.getKey())
                .config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
                .when()
                .get(Endpoints_Web_Services.WORKSPACE)
                .then()
                .assertThat()
                .statusCode(201);
    }


}
