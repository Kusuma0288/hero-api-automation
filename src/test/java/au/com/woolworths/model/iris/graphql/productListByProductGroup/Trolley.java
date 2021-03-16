
package au.com.woolworths.model.iris.graphql.productListByProductGroup;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class Trolley {
  @JsonProperty("__typename")
  public String typename;
  public Integer minimum;
  public Integer maximum;
  public Integer increment;
  @JsonProperty("default")
  public Integer _default;
  public Object unit;
  public Object inTrolley;
  public String buttonState;
  public String buttonLabel;
  public Object buttonQuantity;

}
