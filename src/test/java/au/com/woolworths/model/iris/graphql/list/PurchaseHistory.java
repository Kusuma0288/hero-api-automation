package au.com.woolworths.model.iris.graphql.list;

import au.com.woolworths.model.iris.graphql.productList.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurchaseHistory {
  private List<Product> products;
  private int totalNumberOfProducts;
  private int nextPage;
}