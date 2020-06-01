package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HomepageResponse {
  private HomepageData data;
  private String type;

  @Override
  public String toString() {
    return "Homepage{" +
            "data=" + data +
            ", type=" + type +
            +'}';
  }

  public HomepageData getData() {
    return data;
  }

  public void setData(HomepageData data) {
    this.data = data;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}



