package au.com.woolworths.model.metis.scan_qr_code;

import lombok.Data;

@Data
public class CustomerInfo {

  private CustomerPayload payload;
  private String schemaId;
}
