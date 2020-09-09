package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import au.com.woolworths.model.common.SubstitutionPreferences;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CheckoutV3Response {
  private Address PrimaryAddress;
  private Order Order;
  private Errors[] Errors;
  private String EdrNumber;
  private Promotion Promotion;
  private String[] StoreCreditCodes;
  private String[] EvoucherCodes;
  private CheckoutWindowResponseV3 Window;
  private Loyalty Loyalty;
  private boolean CanProceedToPayment;
  private String WarningMessage;
  private String FulfilmentMethod;
  private int MaximumExpressOrderQuantity;
  private boolean HasCarePackage;
  private SubstitutionPreferences SubstitutionPreferences;
  
}
