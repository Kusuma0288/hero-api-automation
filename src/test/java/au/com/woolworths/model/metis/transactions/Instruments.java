package au.com.woolworths.model.metis.transactions;

import lombok.Data;

@Data
public class Instruments {

  private int amount;
  private String paymentInstrumentId;
  private String paymentTransactionRef;
}
