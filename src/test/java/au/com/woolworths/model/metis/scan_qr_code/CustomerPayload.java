package au.com.woolworths.model.metis.scan_qr_code;

import lombok.Data;

@Data
public class CustomerPayload {

  private String rewardsId;
  private boolean usePoints;
}
