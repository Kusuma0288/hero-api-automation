package au.com.woolworths.model.iris.graphql.productList;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class Product {

  // When "__typename": "GoogleAdBannerCard" i.e. ad within product list
  private String adUnit;
  private String nativeCustomTemplateId;
  private String iabSize;
  private Targeting targeting;

  @JsonProperty("__typename")
  private String typename;
  private String productId;
  private String name;
  private String productImage;
  private Boolean isAvailable;
  private String purchaseRestriction;
  private Boolean isNew;
  private Integer price;
  private String unitPriceDescription;
  private String badgeImage;
  private PromotionInfo promotionInfo;
  private String promotionValue;
  private String wasPrice;
  private String purchaseWarning;
  private List list;
  private Trolley trolley;
  private InStoreDetails inStoreDetails;
  private String multiBuyPrice;
  private String multiBuyUnitPrice;
  private java.util.List<InlineLabel> inlineLabels;
  private String adId;
}
