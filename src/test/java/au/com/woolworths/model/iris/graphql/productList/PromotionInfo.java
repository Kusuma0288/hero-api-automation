package au.com.woolworths.model.iris.graphql.productList;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

@lombok.Data
@EqualsAndHashCode
public class PromotionInfo {
  @JsonProperty("__typename")
  private String typename;
  private String type;
  private String label;

}
