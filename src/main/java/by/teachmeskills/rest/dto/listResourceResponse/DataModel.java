package by.teachmeskills.rest.dto.listResourceResponse;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
@Builder
@Accessors(chain = true)
public class DataModel {

    public int id;
    public String name;
    public int year;
    public String color;
    @SerializedName("pantone_value")
    public String pantoneValue;
}
