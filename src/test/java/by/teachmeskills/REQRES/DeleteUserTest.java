package by.teachmeskills.REQRES;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class DeleteUserTest {

    @Test
    public void deleteUserTest() {

        String body = given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                log().ifValidationFails().
        when().
                delete("https://reqres.in/api/users/2").
        then().
                statusCode(204).
                log().ifValidationFails().
        extract().body().asString();

        assertThat(body).as("The response isn't empty")
                            .isEmpty();
    }
}
