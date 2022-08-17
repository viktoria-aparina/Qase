package by.teachmeskills.REQRES;

import by.teachmeskills.rest.dto.delayedResponse.DataModel;
import by.teachmeskills.rest.dto.delayedResponse.DelayedResponse;
import by.teachmeskills.rest.dto.delayedResponse.Support;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class GetDelayedResponseTest {

    @Test
    public void delayedResponseTest() {

        DataModel user1 = by.teachmeskills.rest.dto.delayedResponse.DataModel.builder()
                                                                             .id(1)
                                                                             .email("george.bluth@reqres.in")
                                                                             .firstName("George")
                                                                             .lastName("Bluth")
                                                                             .avatar("https://reqres.in/img/faces/1-image.jpg")
                                                                             .build();
        DataModel user2 = by.teachmeskills.rest.dto.delayedResponse.DataModel.builder()
                                                                             .id(2)
                                                                             .email("janet.weaver@reqres.in")
                                                                             .firstName("Janet")
                                                                             .lastName("Weaver")
                                                                             .avatar("https://reqres.in/img/faces/2-image.jpg")
                                                                             .build();
        DataModel user3 = by.teachmeskills.rest.dto.delayedResponse.DataModel.builder()
                                                                             .id(3)
                                                                             .email("emma.wong@reqres.in")
                                                                             .firstName("Emma")
                                                                             .lastName("Wong")
                                                                             .avatar("https://reqres.in/img/faces/3-image.jpg")
                                                                             .build();
        DataModel user4 = by.teachmeskills.rest.dto.delayedResponse.DataModel.builder()
                                                                             .id(4)
                                                                             .email("eve.holt@reqres.in")
                                                                             .firstName("Eve")
                                                                             .lastName("Holt")
                                                                             .avatar("https://reqres.in/img/faces/4-image.jpg")
                                                                             .build();
        DataModel user5 = by.teachmeskills.rest.dto.delayedResponse.DataModel.builder()
                                                                             .id(5)
                                                                             .email("charles.morris@reqres.in")
                                                                             .firstName("Charles")
                                                                             .lastName("Morris")
                                                                             .avatar("https://reqres.in/img/faces/5-image.jpg")
                                                                             .build();
        DataModel user6 = by.teachmeskills.rest.dto.delayedResponse.DataModel.builder()
                                                                             .id(6)
                                                                             .email("tracey.ramos@reqres.in")
                                                                             .firstName("Tracey")
                                                                             .lastName("Ramos")
                                                                             .avatar("https://reqres.in/img/faces/6-image.jpg")
                                                                             .build();
        DelayedResponse expectedDelayedResponse = DelayedResponse.builder()
                                                                 .page(1)
                                                                 .perPage(6)
                                                                 .total(12)
                                                                 .totalPages(2)
                                                                 .data(List.of(user1, user2, user3, user4, user5, user6))
                                                                 .support(Support.builder()
                                                                                 .url("https://reqres.in/#support-heading")
                                                                                 .text("To keep ReqRes free, contributions towards server costs are appreciated!").build()).build();

        DelayedResponse getActualDelayedResponse = given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                log().ifValidationFails().
        when().
                get("https://reqres.in/api/users?delay=3").
        then().
                statusCode(200).
                log().ifValidationFails().
        extract().
                body().as(DelayedResponse.class, ObjectMapperType.GSON);

        assertThat(getActualDelayedResponse).as("The delayed response is different").isEqualTo(expectedDelayedResponse);
    }
}
