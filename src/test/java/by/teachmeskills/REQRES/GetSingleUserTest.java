package by.teachmeskills.REQRES;

import by.teachmeskills.rest.dto.singleUserResponse.DataModel;
import by.teachmeskills.rest.dto.singleUserResponse.SingleUser;
import by.teachmeskills.rest.dto.singleUserResponse.Support;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class GetSingleUserTest {

    @Test
    public void getSingleUserTest() {

        SingleUser expectedUser = SingleUser.builder()
                                            .data(DataModel.builder()
                                                                .id(2)
                                                                .email("janet.weaver@reqres.in")
                                                                .firstName("Janet")
                                                                .lastName("Weaver")
                                                                .avatar("https://reqres.in/img/faces/2-image.jpg")
                                                                .build())
                                            .support(Support.builder()
                                                            .url("https://reqres.in/#support-heading")
                                                            .text("To keep ReqRes free, contributions towards server costs are appreciated!").build()).build();

        SingleUser getActualSingleUser = given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
        when().
                get("https://reqres.in/api/users/2").
        then().
                statusCode(200).
                log().ifValidationFails().
        extract().
                body().as(SingleUser.class, ObjectMapperType.GSON);

        assertThat(getActualSingleUser).as("Users are different").isEqualTo(expectedUser);
    }
}
