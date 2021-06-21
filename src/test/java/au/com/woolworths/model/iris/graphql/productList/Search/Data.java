package au.com.woolworths.model.iris.graphql.productList.Search;

import au.com.woolworths.model.iris.graphql.productList.ProductsBySearch;
import lombok.EqualsAndHashCode;

@lombok.Data
@EqualsAndHashCode
public class Data {
  private ProductsBySearch productsBySearch;
}
