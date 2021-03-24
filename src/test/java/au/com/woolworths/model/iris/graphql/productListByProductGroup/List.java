package au.com.woolworths.model.iris.graphql.productListByProductGroup;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class List {
  @JsonProperty("__typename")
  public String typename;
  public int minimum;
  public int maximum;
  public int increment;
  @JsonProperty("default")
  public int _default;
  public Object unit;

}
