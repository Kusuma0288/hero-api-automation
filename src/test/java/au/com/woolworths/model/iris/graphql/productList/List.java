package au.com.woolworths.model.iris.graphql.productList;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class List {
  @JsonProperty("__typename")
  private String typename;
  private Integer minimum;
  private Integer maximum;
  private Integer increment;
  private Integer _default;
  private String unit;

  @JsonProperty("default")
  public Integer getDefault() {
    return _default;
  }

  @JsonProperty("default")
  public void setDefault(Integer _default) {
    this._default = _default;
  }
}
