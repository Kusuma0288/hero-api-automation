package au.com.woolworths.apigee.model;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data public class ApigeeGuestLoginRequest {
  private String device_auth_token;
  private String store;
  private String postcode;
}
