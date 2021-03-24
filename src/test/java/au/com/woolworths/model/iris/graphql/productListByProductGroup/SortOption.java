package au.com.woolworths.model.iris.graphql.productListByProductGroup;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class SortOption {
  @JsonProperty("__typename")
  public String typename;
  public String key;
  public String title;
  public String subtitle;
  public boolean isApplied;

}
