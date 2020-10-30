package au.com.woolworths.model.metis.transactions;

import lombok.Data;

@Data
public class CreditCards {

  private ReceiptData receiptData;
  private String paymentToken;
  private String externalServiceCode;
  private String paymentInstrumentId;
  private String paymentTransactionRef;
  private String externalServiceMessage;
  private ExtendedTransactionData[] extendedTransactionData;
}
