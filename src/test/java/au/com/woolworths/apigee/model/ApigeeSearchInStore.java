package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApigeeSearchInStore {
  private ApigeeInStores[] stores;

  @Override
  public String toString() {
    return "InStores{" +
        "stores=" + stores +
        +'}';
  }

  public ApigeeInStores[] getstores() {
    return stores;
  }

  public void setstores(ApigeeInStores[] store) {
    stores = store;
  }
}
