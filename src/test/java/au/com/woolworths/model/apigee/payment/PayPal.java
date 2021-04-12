package au.com.woolworths.model.apigee.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PayPal {
  private String paymentInstrumentId;
  private String paymentToken;
  private String status;
  private String createdOn;
  private String lastUpdated;
  private String lastUsed;
  private Boolean primary;
  private Boolean allowed;
  private String customerId;
  private String payPalId;
}
