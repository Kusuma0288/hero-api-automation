package au.com.woolworths.model.metis.transactions;

import lombok.Data;

@Data
public class FraudResponse {

  private String clientId;
  private String decision;
  private String reasonCode;
}
