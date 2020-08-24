package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PaywithPayPalResponse {

  private boolean Success;
  private String ErrorMessage;
  private ResponseStatus ResponseStatus;
  private Object PlacedOrderId;
  private Object Message;
  private String statusCode;

}

