package au.com.woolworths.model.iris.graphql.changeMyOrder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderSummary {
    
  @JsonProperty("__typename")
  private String typename;
  private int orderId;
  private Feed feed;
}
