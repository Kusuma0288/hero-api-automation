package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HomepageData {
  private HomepageComponents[] Items;

  @Override
  public String toString() {
    return "HomepageData{" +
        "Items=" + Items +
        +'}';
  }

  public HomepageComponents[] getItems() {
    return Items;
  }

  public void setItems(HomepageComponents[] items) {
    Items = items;
  }
}



