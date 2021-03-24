package au.com.woolworths.model.iris.graphql.productListByProductGroup;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class Product {
  @JsonProperty("__typename")
  public String typename;
  public String productId;
  public String name;
  public boolean isNew;
  public String purchaseRestriction;
  public String productImage;
  public boolean isAvailable;
  public int price;
  public String badgeImage;
  public Object promotionInfo;
  public String unitPriceDescription;
  public Object wasPrice;
  public Object purchaseWarning;
  public String multiBuyPrice;
  public String multiBuyUnitPrice;
  public java.util.List<InlineLabel> inlineLabels = null;
  public Trolley trolley;
  public au.com.woolworths.model.iris.graphql.productListByProductGroup.List list;
  public Object inStoreDetails;
  public Object adId;

}
