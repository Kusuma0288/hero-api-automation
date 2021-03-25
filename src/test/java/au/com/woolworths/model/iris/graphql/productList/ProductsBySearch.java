package au.com.woolworths.model.iris.graphql.productList;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductsBySearch {
  private Integer totalNumberOfProducts;
  private Integer nextPage;
  private List<Filter> filters = null;
  private List<SortOption> sortOptions = null;
  private List<Product> products = null;
}
