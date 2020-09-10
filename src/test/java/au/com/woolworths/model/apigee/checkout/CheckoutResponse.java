package au.com.woolworths.model.apigee.checkout;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CheckoutResponse {
  private Order Order;
  private CheckoutPackagingPreferencesResponse[] DeliveryPackagingPreferences;
  private CheckoutFulfilmentWindows[] FulfilmentWindows;
  private CheckoutResultsResponse Results;
}
