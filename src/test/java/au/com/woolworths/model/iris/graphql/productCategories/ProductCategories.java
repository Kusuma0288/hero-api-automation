
package au.com.woolworths.model.iris.graphql.productCategories;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductCategories {
  private String headerTitle;
  private List <Categories> categories;
}