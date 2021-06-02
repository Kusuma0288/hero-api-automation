package au.com.woolworths.model.iris.graphql.list;

import au.com.woolworths.model.iris.graphql.productList.Product;
import com.fasterxml.jackson.annotation.JsonInclude;

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductsByProductIds {
  private Product[] products;
}