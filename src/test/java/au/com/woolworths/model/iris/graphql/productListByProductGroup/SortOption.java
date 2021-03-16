
package au.com.woolworths.model.iris.graphql.productListByProductGroup;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class SortOption {
  @JsonProperty("__typename")
  public String typename;
  public String key;
  public String title;
  public Object subtitle;
  public Boolean isApplied;

}
