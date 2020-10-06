package au.com.woolworths.model.iris.graphql.productList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Trolley {
  private Integer minimum;
  private Integer maximum;
  private Integer increment;
  private Float inTrolley;
  private String unit;
  private String buttonState;
  private String buttonLabel;
  private String buttonQuantity;
  @JsonProperty("default")
  private Integer _default;

  @JsonProperty("default")
  public Integer getDefault() {
    return _default;
  }

  @JsonProperty("default")
  public void setDefault(Integer _default) {
    this._default = _default;
  }
}
