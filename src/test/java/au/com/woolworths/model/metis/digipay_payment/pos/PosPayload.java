package au.com.woolworths.model.metis.digipay_payment.pos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PosPayload {

  private String schemaId;
  private Payload payload;
}
