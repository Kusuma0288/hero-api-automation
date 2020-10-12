package au.com.woolworths.model.iris.graphql.productDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryOfOrigin {
  @JsonProperty("__typename")
  private String typename;
  private String url;
  private String text;
  private String altText;
}
