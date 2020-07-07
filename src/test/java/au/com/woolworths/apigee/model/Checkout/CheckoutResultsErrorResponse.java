package au.com.woolworths.apigee.model.Checkout;

import lombok.Data;

@Data public class CheckoutResultsErrorResponse {
  private int httpStatusCode;
  private int errorCode;
  private String errorMessage;
  private String errorDetail;
  private String method;
  private String function;
}
