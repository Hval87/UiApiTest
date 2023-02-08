package util;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import java.io.File;
import java.util.Map;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j;
import static io.restassured.RestAssured.given;


@Log4j
public class APIUtil {
    private static String URL;
    private static String fullRequestPath;

    static {
        URL = DataManager.getConfigValue("url");
    }

    public static Response getResponsePOST(String paramName, Integer paramValue, String uri) {
        return given().when()
                .queryParam(paramName, paramValue)
                .log().all()
                .post(URL + uri)
                .then()
                .log().headers()
                .extract().response();
    }
    public static Response getResponsePOST(Map<String,String> params, String uri) {
        return given().when()
                .params(params)
                .log().all()
                .post(URL + uri)
                .then()
                .log().all()
                .extract().response();
    }

    public static void checkIsJson(Response response, String schema) {
        String schemaPath = FileProvider.getTestPath(schema);
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(schemaPath)));
    }
}