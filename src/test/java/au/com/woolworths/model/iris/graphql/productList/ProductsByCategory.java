package au.com.woolworths.model.iris.graphql.productList;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;

import java.util.List;

@lombok.Data
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductsByCategory {
  private Integer totalNumberOfProducts;
  private Integer nextPage;
  private List<Filter> filters;
  private List<SortOption> sortOptions;
  @EqualsAndHashCode.Exclude
  private List<Product> products;
}
