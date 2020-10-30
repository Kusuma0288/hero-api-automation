package au.com.woolworths.model.metis.digipay_payment;

import lombok.Data;

@Data
public class PaymentSessionResponse {

  private PaymentSessionResponseData data;
  private Meta meta;
}