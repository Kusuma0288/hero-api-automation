package au.com.woolworths.model.apigee.checkout;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CheckoutFulfilmentWindows {

  private boolean IsReserved;
  private String StartsFrom;
  private String Date;
  private Boolean IsAvailable;
  private String DateText;
  private boolean IsEligibleForDeliverySaver;
  private CheckoutWindowItems Morning;
  private CheckoutWindowItems Afternoon;
  private CheckoutWindowItems Evening;
  private DeliveryNowWindows DeliveryNow;
}
