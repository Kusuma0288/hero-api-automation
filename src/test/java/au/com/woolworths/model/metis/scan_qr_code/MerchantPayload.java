package au.com.woolworths.model.metis.scan_qr_code;

import lombok.Data;

@Data
public class MerchantPayload {

  private String laneId;
  private String storeId;
}