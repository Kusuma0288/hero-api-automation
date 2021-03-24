package au.com.woolworths.model.iris.graphql.productListByProductGroup;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@lombok.Data
public class ProductsByProductGroup {
  @JsonProperty("__typename")
  public String typename;
  public int totalNumberOfProducts;
  public int nextPage;
  public List<Product> products = null;
  public List<Object> filters = null;
  public List<SortOption> sortOptions = null;

}
