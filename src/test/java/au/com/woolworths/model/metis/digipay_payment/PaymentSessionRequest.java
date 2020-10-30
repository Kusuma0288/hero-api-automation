package au.com.woolworths.model.metis.digipay_payment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentSessionRequest {

  private PaymentSessionData data;
  private Meta meta;
}
