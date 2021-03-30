package au.com.woolworths.model.apigee.authentication;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class GuestLoginRequest {
  private String device_auth_token;
  private String store;
  private String postcode;
}
