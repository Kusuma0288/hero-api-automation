package au.com.woolworths.model.metis.specials;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ProductsData {
  private String name;
  private Long price;
  private String productId;
  private String productImage;
  private PromotionValue promotionValue;
  private String unitPriceDescription;
}
