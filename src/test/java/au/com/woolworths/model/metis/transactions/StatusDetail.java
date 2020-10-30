package au.com.woolworths.model.metis.transactions;

import lombok.Data;

@Data
public class StatusDetail {

  private String[] payPal;
  private String[] unknown;
  private String[] applePay;
  private String[] giftCards;
  private String[] googlePay;
  private String[] androidPay;
  private CreditCards[] creditCards;
  private FraudResponse fraudResponse;
  private boolean partialSuccess;
  private String transactionReceipt;
}
