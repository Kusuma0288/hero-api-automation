
package au.com.woolworths.model.iris.graphql.productListByProductGroup;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class List {
  @JsonProperty("__typename")
  public String typename;
  public Integer minimum;
  public Integer maximum;
  public Integer increment;
  @JsonProperty("default")
  public Integer _default;
  public Object unit;

}
