
package au.com.woolworths.model.iris.graphql.productList;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductsBySearch {
  private Integer totalNumberOfProducts;
  private Integer nextPage;
  private List<Filter> filters = null;
  private List<SortOption> sortOptions = null;
  private List<Product> products = null;
}
