package au.com.woolworths.model.iris.graphql.productCategories;

import com.fasterxml.jackson.annotation.JsonInclude;

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductCategoriesResponse {
  private Data data;
}