package by.teachmeskills.REQRES;

import by.teachmeskills.rest.dto.listUserResponse.DataModel;
import by.teachmeskills.rest.dto.listUserResponse.ListUsers;
import by.teachmeskills.rest.dto.listUserResponse.Support;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class GetListOfUsersTest {

    @Test
    public void listOfUsersTest() {

        DataModel user7 = DataModel.builder()
                                   .id(7)
                                   .email("michael.lawson@reqres.in")
                                   .firstName("Michael")
                                   .lastName("Lawson")
                                   .avatar("https://reqres.in/img/faces/7-image.jpg")
                                   .build();
        DataModel user8 = DataModel.builder()
                                   .id(8)
                                   .email("lindsay.ferguson@reqres.in")
                                   .firstName("Lindsay")
                                   .lastName("Ferguson")
                                   .avatar("https://reqres.in/img/faces/8-image.jpg")
                                   .build();
        DataModel user9 = DataModel.builder()
                                   .id(9)
                                   .email("tobias.funke@reqres.in")
                                   .firstName("Tobias")
                                   .lastName("Funke")
                                   .avatar("https://reqres.in/img/faces/9-image.jpg")
                                   .build();
        DataModel user10 = DataModel.builder()
                                    .id(10)
                                    .email("byron.fields@reqres.in")
                                    .firstName("Byron")
                                    .lastName("Fields")
                                    .avatar("https://reqres.in/img/faces/10-image.jpg")
                                    .build();
        DataModel user11 = DataModel.builder()
                                    .id(11)
                                    .email("george.edwards@reqres.in")
                                    .firstName("George")
                                    .lastName("Edwards")
                                    .avatar("https://reqres.in/img/faces/11-image.jpg")
                                    .build();
        DataModel user12 = DataModel.builder()
                                    .id(12)
                                    .email("rachel.howell@reqres.in")
                                    .firstName("Rachel")
                                    .lastName("Howell")
                                    .avatar("https://reqres.in/img/faces/12-image.jpg")
                                    .build();

        ListUsers expectedList = ListUsers.builder()
                                          .page(2)
                                          .perPage(6)
                                          .total(12)
                                          .totalPages(2)
                                          .data(List.of(user7, user8, user9, user10, user11, user12))
                                          .support(Support.builder()
                                                          .url("https://reqres.in/#support-heading")
                                                          .text("To keep ReqRes free, contributions towards server costs are appreciated!").build()).build();

        ListUsers getActualListUsers = given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
        when().
                get("https://reqres.in/api/users?page=2").
        then().
                statusCode(200).
                log().ifValidationFails().
        extract().
                body().as(ListUsers.class, ObjectMapperType.GSON);

        assertThat(getActualListUsers).as("Lists are different").isEqualTo(expectedList);
    }
}
