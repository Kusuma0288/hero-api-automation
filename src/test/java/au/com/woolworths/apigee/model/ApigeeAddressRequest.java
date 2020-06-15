package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApigeeAddressRequest {

  private String AmasId;

  @Override
  public String toString() {
    return "AddressRequest{" +
        "AmasId='" + AmasId + '\'' +
        '}';
  }

  public String getAmasId() {
    return AmasId;
  }

  public void setAmasId(String amasId) {
    AmasId = amasId;
  }

}

