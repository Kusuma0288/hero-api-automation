package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HomepageItems {
  private HomepageItemsData data;
  private String type;

  @Override
  public String toString() {
    return "ProductGroupResponse{" +
            "data=" + data +
            ", type=" + type +
            +'}';
  }

  public HomepageItemsData getData() {
    return data;
  }

  public void setData(HomepageItemsData data) {
    this.data = data;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}



