package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApigeeListAddresses {
  private ApigeeAddressDetails[] Addresses;

  @Override
  public String toString() {
    return "Addresses{" +
            "addresses=" + Addresses +
            '}';
  }

  public ApigeeAddressDetails[] getAddresses() {
    return Addresses;
  }

  public void setAddresses(ApigeeAddressDetails[] address) {
    Addresses = address;
  }
}
