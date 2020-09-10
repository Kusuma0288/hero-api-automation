package au.com.woolworths.model.apigee.authentication;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class GuestLoginRequest {
  private String device_auth_token;
  private String store;
  private String postcode;
}
