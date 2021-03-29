package au.com.woolworths.model.iris.graphql.productList;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Filter {
  private String headerKey;
  private String headerTitle;
  private List<FilterItem> filterItems = null;
}
