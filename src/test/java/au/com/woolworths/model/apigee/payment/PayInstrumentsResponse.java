package au.com.woolworths.model.apigee.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PayInstrumentsResponse {
  private String[] creditCards;
  private String[] giftCards;
  private PayPal[] payPal;
  private String[] paymentAgreements;
  private String androidPay;
  private String googlePay;
  private String applePay;
}
