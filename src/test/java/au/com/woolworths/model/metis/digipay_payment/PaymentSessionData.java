package au.com.woolworths.model.metis.digipay_payment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentSessionData {

  private String location;
  private MerchantInfo merchantInfo;
  private boolean generateQR;
  private int timeToLiveQR;
  private int timeToLivePaymentSession;
}
