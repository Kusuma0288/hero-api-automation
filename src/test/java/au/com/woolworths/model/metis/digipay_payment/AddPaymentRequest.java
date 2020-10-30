package au.com.woolworths.model.metis.digipay_payment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddPaymentRequest {

  private AddPaymentRequestData data;
  private Meta meta;
}
