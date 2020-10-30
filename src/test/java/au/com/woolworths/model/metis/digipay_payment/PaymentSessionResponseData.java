package au.com.woolworths.model.metis.digipay_payment;

import lombok.Data;

@Data
public class PaymentSessionResponseData {

  private String paymentSessionId;
  private QR qr;
}
