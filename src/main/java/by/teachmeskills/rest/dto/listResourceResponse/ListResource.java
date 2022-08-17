package by.teachmeskills.rest.dto.listResourceResponse;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
@Builder
@Accessors(chain = true)
public class ListResource {

    public int page;
    @SerializedName("per_page")
    public int perPage;
    public int total;
    @SerializedName("total_pages")
    public int totalPages;
    public List<DataModel> data;
    public Support support;
}
