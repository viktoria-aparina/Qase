package by.teachmeskills.REQRES;

import by.teachmeskills.rest.dto.listResourceResponse.DataModel;
import by.teachmeskills.rest.dto.listResourceResponse.ListResource;
import by.teachmeskills.rest.dto.listResourceResponse.Support;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class GetListResourceTest {

    @Test
    public void listResourceTest() {

        DataModel id1 = DataModel.builder()
                                 .id(1)
                                 .name("cerulean")
                                 .year(2000)
                                 .color("#98B2D1")
                                 .pantoneValue("15-4020")
                                 .build();

        DataModel id2 = DataModel.builder()
                                 .id(2)
                                 .name("fuchsia rose")
                                 .year(2001)
                                 .color("#C74375")
                                 .pantoneValue("17-2031")
                                 .build();

        DataModel id3 = DataModel.builder()
                                 .id(3)
                                 .name("true red")
                                 .year(2002)
                                 .color("#BF1932")
                                 .pantoneValue("19-1664")
                                 .build();

        DataModel id4 = DataModel.builder()
                                 .id(4)
                                 .name("aqua sky")
                                 .year(2003)
                                 .color("#7BC4C4")
                                 .pantoneValue("14-4811")
                                 .build();

        DataModel id5 = DataModel.builder()
                                 .id(5)
                                 .name("tigerlily")
                                 .year(2004)
                                 .color("#E2583E")
                                 .pantoneValue("17-1456")
                                 .build();

        DataModel id6 = DataModel.builder()
                                 .id(6)
                                 .name("blue turquoise")
                                 .year(2005)
                                 .color("#53B0AE")
                                 .pantoneValue("15-5217")
                                 .build();

        ListResource expectedList = ListResource.builder()
                                                .page(1)
                                                .perPage(6)
                                                .total(12)
                                                .totalPages(2)
                                                .data(List.of(id1, id2, id3, id4, id5, id6))
                                                .support(Support.builder()
                                                                .url("https://reqres.in/#support-heading")
                                                                .text("To keep ReqRes free, contributions towards server costs are appreciated!").build()).build();

        ListResource getActualListResource = given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
        when().
                get("https://reqres.in/api/unknown").
        then().
                statusCode(200).
                log().ifValidationFails().
        extract().
                body().as(ListResource.class, ObjectMapperType.GSON);

        assertThat(getActualListResource).as("Lists are different").isEqualTo(expectedList);
    }
}
