package by.teachmeskills.rest.dto.singleRecourseResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor
@Builder
public class DataModel {

    public int id;
    public String name;
    public int year;
    public String color;
    @SerializedName("pantone_value")
    public String pantoneValue;
}