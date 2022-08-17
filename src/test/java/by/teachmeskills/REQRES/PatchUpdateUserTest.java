package by.teachmeskills.REQRES;

import by.teachmeskills.rest.dto.User;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.mapper.ObjectMapperType.GSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PatchUpdateUserTest {

    @Test
    public void patchUserTest() {

        User expectedUser = User.builder()
                                .userName("morpheus")
                                .job("zion resident").build();

        User actualUser = given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(expectedUser, GSON).
                log().ifValidationFails().
        when().
                patch("https://reqres.in/api/users/2").
        then().
                statusCode(200).
                log().ifValidationFails().
                body("userName", equalTo("morpheus"), "job", equalTo("zion resident")).
         extract().body().as(User.class);

        assertThat(actualUser).as("The user in the response doesn't match expected user")
                              .usingRecursiveComparison()
                              .ignoringFields("updatedAt")
                              .isEqualTo(expectedUser);

        assertThat(actualUser.getUpdatedAt()).as("The \"updatedAt\" isn't generated")
                                             .isNotNull();
    }
}
