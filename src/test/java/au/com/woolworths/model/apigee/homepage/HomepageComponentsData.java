package au.com.woolworths.model.apigee.homepage;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
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
  private HomepageQueryParameters queryParameters;
  private String id;
  private String deliveryNowStatus;
  private String status;
  private String isEligible;
  private String isAvailable;
  private String isSelected;
  private String deliveryFee;
  private String eta;
  private String etaInMinutes;

}



