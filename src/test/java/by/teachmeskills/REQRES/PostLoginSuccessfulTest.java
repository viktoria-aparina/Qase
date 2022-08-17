package by.teachmeskills.REQRES;

import by.teachmeskills.rest.dto.User;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.mapper.ObjectMapperType.GSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

public class PostLoginSuccessfulTest {

    @Test
    public void loginSuccessfulTest() {

        String expectedToken = "QpwL5tke4Pnpja7X4";

        User expectedUser = User.builder()
                                .email("eve.holt@reqres.in")
                                .password("cityslicka").build();

        User actualUser = given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(expectedUser, GSON).
                log().ifValidationFails().
        when().post("https://reqres.in/api/login").
        then().statusCode(200).
                log().ifValidationFails().
                body("token", equalTo("QpwL5tke4Pnpja7X4")).
        extract().body().as(User.class);

        assertThat(actualUser.getToken()).as("The \"token\" doesn't match").isEqualTo(expectedToken);
    }
}
