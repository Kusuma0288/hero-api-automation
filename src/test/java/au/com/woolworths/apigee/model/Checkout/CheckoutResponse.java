package au.com.woolworths.apigee.model.Checkout;

import lombok.Data;
import au.com.woolworths.apigee.model.Order;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class CheckoutResponse {
  private Order Order;
  private CheckoutPackagingPreferencesResponse[] DeliveryPackagingPreferences;
  private CheckoutFulfilmentWindows[] FulfilmentWindows;
  private CheckoutResultsResponse Results;
}
