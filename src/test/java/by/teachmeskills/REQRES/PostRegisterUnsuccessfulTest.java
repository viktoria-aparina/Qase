package by.teachmeskills.REQRES;

import by.teachmeskills.rest.dto.User;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.mapper.ObjectMapperType.GSON;
import static org.assertj.core.api.Assertions.assertThat;

public class PostRegisterUnsuccessfulTest {

    String expectedError = "Missing password";

        @Test
        public void postRegisterUnsuccessfulTest() {
            User expectedUser = User.builder()
                                    .email("sydney@fife")
                                    .build();

            String actualError = given().
                    contentType(ContentType.JSON).
                    accept(ContentType.JSON).
                    body(expectedUser, GSON).
                    log().ifValidationFails().
            when().
                    post("https://reqres.in/api/register").
            then().
                    statusCode(400).log().ifValidationFails().
            extract().body().jsonPath().getString("error");

            assertThat(actualError).as("The error doesn't match")
                                   .isEqualTo(expectedError);
        }
}
