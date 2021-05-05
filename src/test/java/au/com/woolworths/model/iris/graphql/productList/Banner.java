package au.com.woolworths.model.iris.graphql.productList;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class Banner {
  @JsonProperty("__typename")
  private String typename;
  public String displayType;
  public String message;
  public Action action;
  public String title;
}
