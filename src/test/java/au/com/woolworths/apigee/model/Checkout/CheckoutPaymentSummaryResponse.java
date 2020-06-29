package au.com.woolworths.apigee.model.Checkout;

import au.com.woolworths.apigee.model.Order;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class CheckoutPaymentSummaryResponse {
  private au.com.woolworths.apigee.model.Order Order;
  private Object paymentMessages;
  private Object Results;

}