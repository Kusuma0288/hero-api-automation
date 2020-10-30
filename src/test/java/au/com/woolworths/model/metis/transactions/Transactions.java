package au.com.woolworths.model.metis.transactions;

import lombok.Data;

@Data
public class Transactions {

  private String type;
  private String status;
  private String merchantId;
  private int grossAmount;
  private Instruments[] instruments;
  private StatusDetail statusDetail;
  private String executionTime;
  private String transactionId;
  private String clientReference;
  private String paymentRequestId;
  private String merchantReferenceId;
}
