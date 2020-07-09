package au.com.woolworths.model.apigee.checkout;

import lombok.Data;

@Data public class CheckoutResultsErrorResponse {
  private int httpStatusCode;
  private int errorCode;
  private String errorMessage;
  private String errorDetail;
  private String method;
  private String function;
}
