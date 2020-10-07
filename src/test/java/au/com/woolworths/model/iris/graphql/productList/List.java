package au.com.woolworths.model.iris.graphql.productList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class List {
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
