package au.com.woolworths.model.metis.digipay_payment;

import lombok.Data;

@Data
public class QR {

  private String qrId;
  private String referenceId;
  private String referenceType;
  private String content;
  private String image;
  private String expiryTime;
}
