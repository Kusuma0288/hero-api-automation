package au.com.woolworths.apigee.model;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data public class ApigeeLoginReponse {
  @JsonProperty("access_token")
  private String accessToken;
  @JsonProperty("refresh_token")
  private String refreshToken;
  @JsonProperty("expires_in")
  private String expiresIn;
  @JsonProperty("trolleyhref")
  private String trolleyhref;
  @JsonProperty("fulfilmenthref")
  private String fulfilmenthref;
  @JsonProperty("paymenthref")
  private String paymenthref;
  @JsonProperty("shopperid")
  private String shopperid;
  @JsonProperty("title")
  private String title;
  @JsonProperty("firstname")
  private String firstname;
  @JsonProperty("lastname")
  private String lastname;
  @JsonProperty("email")
  private String email;
  @JsonProperty("dateofbirth")
  private String dateofbirth;
  @JsonProperty("primaryphone")
  private String primaryphone;
  @JsonProperty("secondaryphone")
  private String secondaryphone;
  @JsonProperty("otherphone")
  private String otherphone;
  @JsonProperty("fulfilmentstoreid")
  private String fulfilmentstoreid;
  @JsonProperty("fulfilmentmethod")
  private String fulfilmentmethod;
  @JsonProperty("fulfilmentstorename")
  private String fulfilmentstorename;
  @JsonProperty("deliverymethod")
  private String deliverymethod;
  @JsonProperty("deliverystoreaddressid")
  private String deliverystoreaddressid;
  @JsonProperty("deliverystoreaddresscity")
  private String deliverystoreaddresscity;
  @JsonProperty("deliverystoreaddresscountry")
  private String deliverystoreaddresscountry;
  @JsonProperty("deliveryaddressstreet1")
  private String deliveryaddressstreet1;
  @JsonProperty("deliveryaddressstreet2")
  private String deliveryaddressstreet2;
  @JsonProperty("deliveryaddresssuburb")
  private String deliveryaddresssuburb;
  @JsonProperty("deliveryinstructions")
  private String deliveryinstructions;
  @JsonProperty("isservicable")
  private String isservicable;
  @JsonProperty("rewardscardNumber")
  private String rewardscardNumber;
  @JsonProperty("teammember")
  private String teammember;
  @JsonProperty("trolleymergestatus")
  private String trolleymergestatus;
  private String statusCode;
  private String error;
}
