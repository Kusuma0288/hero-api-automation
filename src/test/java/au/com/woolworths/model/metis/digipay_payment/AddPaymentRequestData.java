package au.com.woolworths.model.metis.digipay_payment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddPaymentRequestData {

  private String paymentSessionId;
  private String location;
  private MerchantInfo merchantInfo;
  private String paymentRequestId;
}
