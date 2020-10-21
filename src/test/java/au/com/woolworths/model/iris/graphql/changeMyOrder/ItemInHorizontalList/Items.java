package au.com.woolworths.model.iris.graphql.changeMyOrder.ItemInHorizontalList;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Items {
    
    @JsonProperty("__typename")
    private String typename;
    private int stockcode;
    private String name;
    private String imagePath;
    private String salesPrice;
    private String pricePerUnit;
    private Float quantity;
    private String icon;
    private String subtitle;
    private String title;

}
