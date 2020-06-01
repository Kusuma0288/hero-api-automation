package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductGroupTrolleyResponse {
  private ProductGroupTrolleyData data;
  private String type;

  @Override
  public String toString() {
    return "ProductGroupTrolleyResponse{" +
            "data=" + data +
            ", type=" + type +
            +'}';
  }

  public ProductGroupTrolleyData getData() {
    return data;
  }

  public void setData(ProductGroupTrolleyData data) {
    this.data = data;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}



