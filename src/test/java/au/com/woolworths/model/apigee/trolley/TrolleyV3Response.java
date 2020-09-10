package au.com.woolworths.model.apigee.trolley;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class TrolleyV3Response {
  private Object Order;
  private TrolleyDetails Trolley;
  private TrolleyResults Results;
}
