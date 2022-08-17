package by.teachmeskills.REQRES;

import by.teachmeskills.rest.dto.User;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.mapper.ObjectMapperType.GSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

public class PostCreateNewUserTest {

    User expectedUser = User.builder()
                            .userName("morpheus")
                            .job("leader").build();

    @Test
    public void createNewUserTest() {
        User actualUser = given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(expectedUser, GSON).
                log().ifValidationFails().
        when().
                post("https://reqres.in/api/users").
        then().
                statusCode(201).
                log().ifValidationFails().
                body("userName", equalTo("morpheus"), "job", equalTo("leader")).
        extract().body().as(User.class);

        assertThat(actualUser).as("The user in the response doesn't match expected user")
                                 .usingRecursiveComparison()
                                 .ignoringFields("createdAt", "id")
                                 .isEqualTo(expectedUser);

        assertThat(actualUser.getId()).as("The \"id\" isn't generated")
                                         .isNotNull();

        assertThat(actualUser.getCreatedAt()).as("The \"createdAt\" isn't generated")
                                      .isNotNull();


    }
}
