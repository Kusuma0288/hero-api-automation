package au.com.woolworths.model.apigee.authentication;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class LoginReponse {
  private String access_token;
  private String refresh_token;
  private String expires_in;
  private String trolleyhref;
  private String fulfilmenthref;
  private String paymenthref;
  private String shopperid;
  private String title;
  private String firstname;
  private String lastname;
  private String email;
  private String dateofbirth;
  private String primaryphone;
  private String secondaryphone;
  private String otherphone;
  private String fulfilmentstoreid;
  private String fulfilmentmethod;
  private String fulfilmentstorename;
  private String deliverymethod;
  private String deliverystoreaddressid;
  private String deliverystoreaddresscity;
  private String deliverystoreaddresscountry;
  private String deliveryaddressstreet1;
  private String deliveryaddressstreet2;
  private String deliveryaddresssuburb;
  private String deliveryinstructions;
  private String isservicable;
  private String rewardscardNumber;
  private String teammember;
  private String trolleymergestatus;
  private String statusCode;
  private String error;


}
