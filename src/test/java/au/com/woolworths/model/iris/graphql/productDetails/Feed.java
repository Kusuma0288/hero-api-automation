package au.com.woolworths.model.iris.graphql.productDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Feed {
  @JsonProperty("__typename")
  private String typename;
  private String productId;
  private java.util.List<InlineLabel> inlineLabels = null;
  private Object multiBuyPrice;
  private Object multiBuyUnitPrice;
  private String name;
  private String productImage;
  private Boolean isAvailable;
  private Boolean isNew;
  private String purchaseRestriction;
  private Integer price;
  private String unitPriceDescription;
  private String badgeImage;
  private Object promotionValue;
  private Object promotionType;
  private Object wasPrice;
  private Object purchaseWarning;
  private Object inStoreDetails;
  private Trolley trolley;
  private List list;
  private String title;
  private String content;
  private Object framedContent;
  private Boolean isCollapsable;
  private CountryOfOrigin countryOfOrigin;
  private HealthStarRating healthStarRating;
  private String servingsPerPack;
  private String servingSize;
  private String bottomCaption;
  private java.util.List<String> tableHeaderRow = null;
  private java.util.List<java.util.List<String>> tableRows = null;
  private Boolean hasIcon;
  private String style;
  private String url;
  private String text;
  private String altText;
}
