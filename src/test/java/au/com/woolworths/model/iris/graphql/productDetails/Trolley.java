package au.com.woolworths.model.iris.graphql.productDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Trolley {
  @JsonProperty("__typename")
  private String typename;
  private Integer minimum;
  private Integer maximum;
  private Integer increment;
  @JsonProperty("default")
  private Integer _default;
  private Object inTrolley;
  private Object unit;
  private String buttonState;
  private String buttonLabel;
  private Object buttonQuantity;

  @JsonProperty("default")
  public Integer getDefault() {
    return _default;
  }

  @JsonProperty("default")
  public void setDefault(Integer _default) {
    this._default = _default;
  }
}
