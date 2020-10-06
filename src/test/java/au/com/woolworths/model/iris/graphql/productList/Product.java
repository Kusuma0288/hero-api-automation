package au.com.woolworths.model.iris.graphql.productList;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {
  private String productId;
  private String name;
  private String productImage;
  private Boolean isAvailable;
  private String purchaseRestriction;
  private Boolean isNew;
  private Integer price;
  private String unitPriceDescription;
  private String badgeImage;
  private String promotionValue;
  private String wasPrice;
  private String purchaseWarning;
  private List list;
  private Trolley trolley;
  private InStoreDetails inStoreDetails;
  private String multiBuyPrice;
  private String multiBuyUnitPrice;
  private java.util.List<InlineLabel> inlineLabels = null;
}
