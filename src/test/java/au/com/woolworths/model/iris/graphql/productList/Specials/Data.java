package au.com.woolworths.model.iris.graphql.productList.Specials;

import au.com.woolworths.model.iris.graphql.productList.ProductsBySpecialsGroup;
import lombok.EqualsAndHashCode;

@lombok.Data
@EqualsAndHashCode
public class Data {
  private ProductsBySpecialsGroup productsBySpecialsGroup;
}
