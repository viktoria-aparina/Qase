package by.teachmeskills.REQRES;

import by.teachmeskills.rest.dto.singleRecourseResponse.DataModel;
import by.teachmeskills.rest.dto.singleRecourseResponse.SingleResource;
import by.teachmeskills.rest.dto.singleRecourseResponse.Support;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class GetSingleResourceTest {

    @Test
    public void singleResourceTest() {

        SingleResource expectedResource = SingleResource.builder()
                                                        .data(DataModel.builder()
                                                                            .id(2)
                                                                            .name("fuchsia rose")
                                                                            .year(2001)
                                                                            .color("#C74375")
                                                                            .pantoneValue("17-2031")
                                                                            .build())
                                                        .support(Support.builder()
                                                                        .url("https://reqres.in/#support-heading")
                                                                        .text("To keep ReqRes free, contributions towards" +
                                                                                      " server costs are appreciated!")
                                                                        .build()).build();

        SingleResource getActualSingleResource = given().
                                                        contentType(ContentType.JSON).
                                                        accept(ContentType.JSON).
        when().
                get("https://reqres.in/api/unknown/2").
        then().
                statusCode(200).
                log().ifValidationFails().
        extract().body().as(SingleResource.class, ObjectMapperType.GSON);
        assertThat(getActualSingleResource).as("Resource are different").isEqualTo(expectedResource);
    }
}