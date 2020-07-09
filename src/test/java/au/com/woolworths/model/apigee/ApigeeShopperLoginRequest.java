package au.com.woolworths.model.apigee;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data public class ApigeeShopperLoginRequest {
  private String device_auth_token;
  private String user_name;
  private String password;
}
