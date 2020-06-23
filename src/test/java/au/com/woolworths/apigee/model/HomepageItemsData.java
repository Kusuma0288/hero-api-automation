package au.com.woolworths.apigee.model;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class HomepageItemsData {
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
  private String title;
  private String subtitle;
  private String status;
  private String id;
  private String total;
  private String timeLabel;
  private String timeValue;
  private String date;
  private String icon;
}



