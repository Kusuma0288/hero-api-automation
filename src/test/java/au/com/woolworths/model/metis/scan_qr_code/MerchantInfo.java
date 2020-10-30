package au.com.woolworths.model.metis.scan_qr_code;

import lombok.Data;

@Data
public class MerchantInfo {

  private String schemaId;
  private MerchantPayload payload;
}
