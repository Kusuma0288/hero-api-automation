package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class Values {

  @JsonProperty("Avg Qty Per Serving")
  private String avgQtyPerServing;
  @JsonProperty("Avg Qty Per 100g")
  private String avgQtyPer100g;

}
