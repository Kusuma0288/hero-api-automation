package au.com.woolworths.model.iris.graphql.productList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import java.util.List;

@lombok.Data
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductsBySearch {
  @JsonProperty("__typename")
  private String typename;
  private Integer totalNumberOfProducts;
  private Integer nextPage;
  private List<Filter> filters;
  private List<SortOption> sortOptions;
  @EqualsAndHashCode.Exclude
  private List<Product> products;
  // Alternative list of products when productsFeed==true
  @EqualsAndHashCode.Exclude
  private List<Product> productsFeed;
}
