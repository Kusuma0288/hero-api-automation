package au.com.woolworths.model.apigee.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class DigitalPayRequest {

  private Payments[] Payments;

  @Data public static class Payments {
    private String stepUpToken;
    private String PaymentInstrumentId;
    private String Amount;
  }
}
