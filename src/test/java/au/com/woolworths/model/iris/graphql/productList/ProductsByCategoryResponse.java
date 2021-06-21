package au.com.woolworths.model.iris.graphql.productList;

import au.com.woolworths.model.iris.graphql.productList.Specials.Categories.Data;
import lombok.EqualsAndHashCode;

@lombok.Data
@EqualsAndHashCode
public class ProductsByCategoryResponse {
  @EqualsAndHashCode.Exclude
  private Data data;
}