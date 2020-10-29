package au.com.woolworths.model.metis.transactions;

import lombok.Data;

@Data
public class ReceiptData {

  private String scheme;
  private String cardSuffix;
  private String expiryYear;
  private String expiryMonth;
}
