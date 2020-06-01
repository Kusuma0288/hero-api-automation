package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class StoreAddressRequest {
  private String storeAddressId;

  @Override
  public String toString() {
    return "StoreAddressRequest{" +
            "storeAddressId='" + storeAddressId + '\'' +
            '}';
  }

  public String getStoreAddressId() {
    return storeAddressId;
  }

  public void setStoreAddressId(String storeAddressId) {
    this.storeAddressId = storeAddressId;
  }
}
