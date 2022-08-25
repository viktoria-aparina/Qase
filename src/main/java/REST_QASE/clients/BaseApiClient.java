package REST_QASE.clients;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseApiClient {

    RequestSpecification rqSpec;
    RequestSpecification rqSpecWithInvalidToken;
    private final static String TOKEN = "567843fe1830148b87fe314fa2af79691f543a93";
    private final static String INVALID_TOKEN = "123T";

    public BaseApiClient() {

        rqSpec = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Token", TOKEN)
                .baseUri("https://api.qase.io/")
                .log().ifValidationFails();

        rqSpecWithInvalidToken = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Token", INVALID_TOKEN)
                .baseUri("https://api.qase.io/")
                .log().ifValidationFails();
    }

    public Response post(String uri, Object body) {
        return given().spec(rqSpec)
                      .body(body)
                      .log().ifValidationFails()
                      .when()
                      .post(uri)
                      .then()
                      .log().ifValidationFails()
                      .extract()
                      .response();
    }

    public Response postWithInvalidToken(String uri, Object body) {
        return given().spec(rqSpecWithInvalidToken)
                      .body(body)
                      .log().ifValidationFails()
                      .when()
                      .post(uri)
                      .then()
                      .log().ifValidationFails()
                      .extract()
                      .response();
    }

    public Response get(String uri, Map<String, ?> parameterNameValuePairs) {
        return given().spec(rqSpec)
                      .pathParams(parameterNameValuePairs)
                      .when()
                      .get(uri)
                      .then()
                      .log().ifValidationFails()
                      .extract()
                      .response();
    }

    public Response getAll(String uri, Map<String, ?> parameterNameValuePairs) {
        return given().spec(rqSpec)
                      .queryParams(parameterNameValuePairs)
                      .when()
                      .get(uri)
                      .then()
                      .log().ifValidationFails()
                      .extract()
                      .response();
    }

    public Response getWithInvalidToken(String uri, Map<String, ?> parameterNameValuePairs) {
        return given().spec(rqSpecWithInvalidToken)
                      .pathParams(parameterNameValuePairs)
                      .when()
                      .get(uri)
                      .then()
                      .log().ifValidationFails()
                      .extract()
                      .response();
    }

    public Response delete(String uri, Map<String, ?> parameterNameValuePairs) {
        return given().spec(rqSpec)
                      .pathParams(parameterNameValuePairs)
                      .when()
                      .delete(uri)
                      .then()
                      .log().ifValidationFails()
                      .extract()
                      .response();
    }

    public Response deleteWithInvalidToken(String uri, Map<String, ?> parameterNameValuePairs) {
        return given().spec(rqSpecWithInvalidToken)
                      .pathParams(parameterNameValuePairs)
                      .when()
                      .delete(uri)
                      .then()
                      .log().ifValidationFails()
                      .extract()
                      .response();
    }
}
