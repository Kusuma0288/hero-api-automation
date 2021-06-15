package au.com.woolworths.model.iris.graphql.productList;

import au.com.woolworths.model.iris.graphql.productList.Specials.Data;
import lombok.EqualsAndHashCode;

@lombok.Data
@EqualsAndHashCode
public class ProductsBySpecialResponse {
  @EqualsAndHashCode.Exclude
  private Data data;
}