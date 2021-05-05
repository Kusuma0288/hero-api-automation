package au.com.woolworths.model.iris.graphql.productList;

import lombok.EqualsAndHashCode;

@lombok.Data
@EqualsAndHashCode
public class ProductsBySearchResponse {
  @EqualsAndHashCode.Exclude
  private Data data;
}