package au.com.woolworths.model.apigee.authentication;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class ShopperLoginRequest {
  private String device_auth_token;
  private String user_name;
  private String password;
  private String authorization_token;
}
