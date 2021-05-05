package au.com.woolworths.model.iris.graphql.productList;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class Filter {
  @JsonProperty("__typename")
  private String typename;
  private String headerKey;
  private String headerTitle;
  private List<FilterItem> filterItems;
  private Banner banner;
}
