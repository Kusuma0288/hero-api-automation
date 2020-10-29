package au.com.woolworths.model.metis.scan_qr_code;

import lombok.Data;

@Data
public class QRIDResponseData {

  private String location;
  private String expiryTime;
  private String merchantId;
  private CustomerInfo customerInfo;
  private MerchantInfo merchantInfo;
  private String paymentSessionId;
  private Meta meta;
}
