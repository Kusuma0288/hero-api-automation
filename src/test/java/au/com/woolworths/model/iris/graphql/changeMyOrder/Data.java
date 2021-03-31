package au.com.woolworths.model.iris.graphql.changeMyOrder;

import com.fasterxml.jackson.annotation.JsonInclude;

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data {
  private OrderSummary orderSummary;
}
