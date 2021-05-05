package au.com.woolworths.model.iris.graphql.productList;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class Action {
  @JsonProperty("__typename")
  private String typename;
  private String placement;
  private String type;
  private String label;
  private String url;
}
