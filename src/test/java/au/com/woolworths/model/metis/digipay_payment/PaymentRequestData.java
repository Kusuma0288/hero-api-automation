package au.com.woolworths.model.metis.digipay_payment;

import au.com.woolworths.model.metis.digipay_payment.pos.PosPayload;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentRequestData {

  private String merchantReferenceId;
  private int grossAmount;
  private boolean generateQR;
  private int maxUses;
  private int timeToLivePayment;
  private int timeToLiveQR;
  private String specificWalletId;
  private PosPayload posPayload;
}
