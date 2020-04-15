package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class HomepageComponentsData {
      private String title;
      private String subtitle;
      private String actionTitle;
      private String actionPath;
      private HomepageItems[] items;
      private String image;
      private String productId;
      private String promotionType;
      private String dataPathType;
      private String dataPath;
      private boolean isExternal;
      private boolean requiresAuth;
      private String url;
      private String altText;
      private String height;

    @Override
    public String toString() {
        return "HomepageComponents{" +
                "title=" + title +
                ", subtitle=" + subtitle +
                ", actionTitle=" + actionTitle +
                ", actionPath=" + actionPath +
                ", items=" + items +
                ", image=" + image +
                ", productId=" + productId +
                ", promotionType=" + promotionType +
                ", dataPathType=" + dataPathType +
                ", dataPath=" + dataPath +
                ", isExternal=" + isExternal +
                ", requiresAuth=" + requiresAuth +
                ", url=" + url +
                ", altText=" + altText +
                ", height=" + height +
                +'}';
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

    public String getActionTitle() {
        return actionTitle;
    }

    public void setActionTitle(String actionTitle) {
        this.actionTitle = actionTitle;
    }

    public String getActionPath() {
        return actionPath;
    }

    public void setActionPath(String actionPath) {
        this.actionPath = actionPath;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProductId(){return productId;}
    public void setProductId(String productId){this.productId = productId;}

    public String getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(String promotionType) {
        this.promotionType = promotionType;
    }

    public String getDataPathType() {
        return dataPathType;
    }

    public void setDataPathType(String dataPathType) {
        this.dataPathType = dataPathType;
    }

    public String getDataPath() {
        return dataPath;
    }

    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }

    public HomepageItems[] getItems() {
        return items;
    }

    public void setItems(HomepageItems[] items) {
        this.items = items;
    }

    public boolean getIsExternal(){return isExternal;}
    public void setIsExternal(boolean isExternal){this.isExternal = isExternal;}

    public boolean getRequiresAuth(){return requiresAuth;}
    public void setRequiresAuth(boolean requiresAuth){this.requiresAuth = requiresAuth;}

    public String getURL(){return url;}
    public void setURL(String url){this.url = url;}

    public String getAltText(){return altText;}
    public void setAltText(String altText){this.altText = altText;}

    public String getHeight(){return height;}
    public void setHeight(String height){this.height = height;}

}



