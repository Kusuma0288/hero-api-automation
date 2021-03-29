package au.com.woolworths.model.iris.graphql.productDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDetails {

  @JsonProperty("__typename")
  private String typename;
  private List<Feed> feed = null;
  private List<ImageList> imageList = null;
}
