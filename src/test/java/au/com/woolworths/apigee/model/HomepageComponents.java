package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HomepageComponents {
  private HomepageComponentsData data;
  private String type;

  @Override
  public String toString() {
    return "HomepageComponents{" +
            "data=" + data +
            ", type=" + type +
            +'}';
  }

  public HomepageComponentsData getData() {
    return data;
  }

  public void setData(HomepageComponentsData data) {
    this.data = data;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}



