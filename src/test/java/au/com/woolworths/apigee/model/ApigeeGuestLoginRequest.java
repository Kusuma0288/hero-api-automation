package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ApigeeGuestLoginRequest {
  private String device_auth_token;
  private String store;
  private String postcode;

  @Override
  public String toString() {
    return "ApigeeGuestRequest{" +
        "device_auth_token='" + device_auth_token + '\'' +
        ", store='" + store + '\'' +
        ", postcode='" + postcode + '\'' +
        '}';
  }

  public String getDeviceAuthToken() {
    return device_auth_token;
  }

  public void setDeviceAuthToken(String device_auth_token) {
    this.device_auth_token = device_auth_token;
  }

  public String getStore() {
    return store;
  }

  public void setStore(String store) {
    this.store = store;
  }

  public String getPostcode() {
    return postcode;
  }

  public void setPostcode(String postcode) {
    this.postcode = postcode;
  }
}
