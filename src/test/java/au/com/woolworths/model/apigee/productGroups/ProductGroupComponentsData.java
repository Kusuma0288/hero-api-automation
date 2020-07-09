package au.com.woolworths.model.apigee.productGroups;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class ProductGroupComponentsData {
  private String productId;
  private String name;
  private String productImage;
  private String isAvailable;
  private String isNew;
  private String unitPriceDescription;
  private String promotionValue;
  private String promotionType;
  private String price;
  private ProductGroupTrolleyResponse trolley;
  private String badgeImage;
  private String wasPrice;
  private String purchaseWarning;

}



