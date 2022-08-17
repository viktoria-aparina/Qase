package by.teachmeskills.REQRES;

import by.teachmeskills.rest.dto.User;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.mapper.ObjectMapperType.GSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

public class PostRegisterSuccessfulTest {

    @Test
    public void postRegisterSuccessfulTest() {
        User expectedUser = User.builder()
                                .email("eve.holt@reqres.in")
                                .password("pistol")
                                .build();

        User actualUser = given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(expectedUser, GSON).
                log().ifValidationFails().
        when().
                post("https://reqres.in/api/register").
        then().
                statusCode(200).log().ifValidationFails().
                body("id", not(empty()), "token", equalTo("QpwL5tke4Pnpja7X4")).
        extract().body().as(User.class);

        assertThat(actualUser.getId()).as("The \"id\" doesn't match expected \"id\"")
                                      .isNotNull()
                                      .isEqualTo(4);
        assertThat(actualUser.getToken()).as("The \"token\" doesn't match expected \"token\"")
                                      .isNotNull()
                                      .isEqualTo("QpwL5tke4Pnpja7X4");
    }
}
