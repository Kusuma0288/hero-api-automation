package au.com.woolworths.model.iris.graphql.productList;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

@lombok.Data
@EqualsAndHashCode
public class Analytics {
  @JsonProperty("__typename")
  public String typename;
  public String adobeTnta;
  public String adobePe;
}
