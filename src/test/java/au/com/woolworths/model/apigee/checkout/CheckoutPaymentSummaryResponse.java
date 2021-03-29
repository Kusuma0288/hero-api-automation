package au.com.woolworths.model.apigee.checkout;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CheckoutPaymentSummaryResponse {
  private Order Order;
  private Object paymentMessages;
  private Object Results;

}