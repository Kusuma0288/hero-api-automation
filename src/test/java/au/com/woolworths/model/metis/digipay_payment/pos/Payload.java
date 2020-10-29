package au.com.woolworths.model.metis.digipay_payment.pos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Payload {

  private String storeId;
  private String laneId;
  private String rrn;
  private long transactionTimestamp;
}
