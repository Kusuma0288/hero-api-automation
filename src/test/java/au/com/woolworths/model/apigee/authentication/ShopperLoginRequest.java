package au.com.woolworths.model.apigee.authentication;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class ShopperLoginRequest {
  private String device_auth_token;
  private String user_name;
  private String password;
}
