package au.com.woolworths.model.iris.graphql.productDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class List {
  @JsonProperty("__typename")
  private String typename;
  private Integer minimum;
  private Integer maximum;
  private Integer increment;
  @JsonProperty("default")
  private Integer _default;
  private Object unit;

  @JsonProperty("default")
  public Integer getDefault() {
    return _default;
  }

  @JsonProperty("default")
  public void setDefault(Integer _default) {
    this._default = _default;
  }
}
