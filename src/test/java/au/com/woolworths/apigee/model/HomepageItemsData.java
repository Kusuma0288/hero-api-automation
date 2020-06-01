package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HomepageItemsData {
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

  @Override
  public String toString() {
    return "ProductGroupComponentsData{" +
            "productId=" + productId +
            ", name=" + name +
            ", productImage=" + productImage +
            ", isAvailable=" + isAvailable +
            ", isNew=" + isNew +
            ", unitPriceDescription=" + unitPriceDescription +
            ", badgeImage=" + badgeImage +
            ", promotionValue=" + promotionValue +
            ", promotionType=" + promotionType +
            ", wasPrice=" + wasPrice +
            ", trolley=" + trolley +
            ", title=" + title +
            ", subtitle=" + subtitle +
            ", status=" + status +
            ", id=" + id +
            ", total=" + total +
            ", timeLabel=" + timeLabel +
            ", timeValue=" + timeValue +
            ", date=" + date +
            ", icon=" + icon +
            +'}';
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getProductImage() {
    return productImage;
  }

  public void setProductImage(String productImage) {
    this.productImage = productImage;
  }

  public String getIsAvailable() {
    return isAvailable;
  }

  public void setIsAvailable(String isAvailable) {
    this.isAvailable = isAvailable;
  }

  public String getIsNew() {
    return isNew;
  }

  public void setIsNew(String isNew) {
    this.isNew = isNew;
  }

  public String getUnitPriceDescription() {
    return unitPriceDescription;
  }

  public void setUnitPriceDescription(String unitPriceDescription) {
    this.unitPriceDescription = unitPriceDescription;
  }

  public String getPromotionValue() {
    return promotionValue;
  }

  public void setPromotionValue(String promotionValue) {
    this.promotionValue = promotionValue;
  }

  public String getPromotionType() {
    return promotionType;
  }

  public void setPromotionType(String promotionType) {
    this.promotionType = promotionType;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public ProductGroupTrolleyResponse getTrolley() {
    return trolley;
  }

  public void setTrolley(ProductGroupTrolleyResponse trolley) {
    this.trolley = trolley;
  }

  public String getBadgeImage() {
    return badgeImage;
  }

  public void setBadgeImage(String badgeImage) {
    this.badgeImage = badgeImage;
  }

  public String getWasPrice() {
    return wasPrice;
  }

  public void setWasPrice(String wasPrice) {
    this.wasPrice = wasPrice;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSubtitle() {
    return subtitle;
  }

  public void setSubtitle(String subtitle) {
    this.subtitle = subtitle;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTotal() {
    return total;
  }

  public void setTotal(String total) {
    this.total = total;
  }

  public String getTimeLabel() {
    return timeLabel;
  }

  public void setTimeLabel(String timeLabel) {
    this.timeLabel = timeLabel;
  }

  public String getTimeValue() {
    return timeValue;
  }

  public void setTimeValue(String timeValue) {
    this.timeValue = timeValue;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }
}



