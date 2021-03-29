package au.com.woolworths.model.iris.graphql.productListByProductGroup;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class Trolley {
  @JsonProperty("__typename")
  public String typename;
  public int minimum;
  public int maximum;
  public int increment;
  @JsonProperty("default")
  public int _default;
  public Object unit;
  public Object inTrolley;
  public String buttonState;
  public String buttonLabel;
  public Object buttonQuantity;

}
