package au.com.woolworths.model.iris.graphql.productListByProductGroup;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class InlineLabel {
  @JsonProperty("__typename")
  public String typename;
  public String type;
  public String label;
  public int priority;

}
