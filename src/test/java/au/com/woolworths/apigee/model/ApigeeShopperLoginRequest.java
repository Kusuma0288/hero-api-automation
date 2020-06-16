package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ApigeeShopperLoginRequest {
  private String device_auth_token;
  private String user_name;
  private String password;

  @Override
  public String toString() {
    return "ApigeeShopperLoginRequest{" +
        "device_auth_token='" + device_auth_token + '\'' +
        ", user_name='" + user_name + '\'' +
        ", password='" + password + '\'' +
        '}';
  }

  public String getDeviceAuthToken() {
    return device_auth_token;
  }

  public void setDeviceAuthToken(String device_auth_token) {
    this.device_auth_token = device_auth_token;
  }

  public String getUserName() {
    return user_name;
  }

  public void setUserName(String user_name) {
    this.user_name = user_name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
