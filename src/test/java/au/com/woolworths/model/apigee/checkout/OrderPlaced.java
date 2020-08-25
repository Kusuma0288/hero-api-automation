package au.com.woolworths.model.apigee.checkout;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class OrderPlaced {
  private OrderConfirmation Order;
  private int ResponseStatus;
}
