package au.com.woolworths.model.metis.transactions;

import lombok.Data;

@Data
public class TransactionsResponse {

  private TransactionsResponseData data;
  private Meta meta;
}
