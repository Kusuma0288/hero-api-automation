package au.com.woolworths.model.iris.graphql.productList;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Filter {
  private String headerKey;
  private String headerTitle;
  private List<FilterItem> filterItems = null;
}
