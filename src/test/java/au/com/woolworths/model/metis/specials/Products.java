package au.com.woolworths.model.metis.specials;

import au.com.woolworths.helpers.metis.deserializers.specials.ProductsDeserializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(using = ProductsDeserializer.class)
@Data
public class Products {

  private String productId;
  private String name;
  private String productImage;
  private double price;
  private String unitPriceDescription;
  private PromotionValue promotionValue;

  public Products(String productId, String name, String productImage, double price, String unitPriceDescription, PromotionValue promotionValue) {
    this.productId = productId;
    this.name = name;
    this.productImage = productImage;
    this.price = price;
    this.unitPriceDescription = unitPriceDescription;
    this.promotionValue = promotionValue;
  }

}
