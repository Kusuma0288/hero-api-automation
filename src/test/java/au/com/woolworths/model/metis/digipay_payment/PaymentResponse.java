package au.com.woolworths.model.metis.digipay_payment;

import lombok.Data;

@Data
public class PaymentResponse {

  private PaymentResponseData data;
  private Meta meta;
}
