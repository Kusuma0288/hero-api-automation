package au.com.woolworths.model.scango.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CreditCard {
  private String paymentInstrumentId;
  private String paymentToken;
  private String status;
  private String lastUpdated;
  private String lastUsed;
  private Boolean primary;
  private Boolean allowed;
  private String expiryYear;
  private Boolean cvvValidated;
  private String expiryMonth;
  private String cardSuffix;
  private String scheme;
  private String cardName;
  private Boolean expired;
  private Boolean requiresCVV;
  private String updateURL;
  private StepUp stepUp;

}
