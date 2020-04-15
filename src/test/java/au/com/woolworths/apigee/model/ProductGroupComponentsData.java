package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductGroupComponentsData {
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

}



