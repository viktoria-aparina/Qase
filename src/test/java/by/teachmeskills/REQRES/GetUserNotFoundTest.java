package by.teachmeskills.REQRES;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class GetUserNotFoundTest {

    @Test
    public void userNotFoundTest() {

        String expectedBody = "{}";

        String body = given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
        when().
                get("https://reqres.in/api/users/23").
        then().
                statusCode(404).
                log().ifValidationFails().
        extract().body().asString();

        assertThat(body).as("The response isn't empty")
                        .isEqualTo(expectedBody);
    }
}
