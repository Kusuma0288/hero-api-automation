package au.com.woolworths.model.apigee.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PayCardCaptureResponse {
  private String cardCaptureURL;
  private String transactionRef;
}
