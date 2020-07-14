package au.com.woolworths.model.apigee.checkout;

import lombok.Data;

@Data public class CheckoutResultsResponse {
  private CheckoutResultsErrorResponse SetDeliveryWindow;
  private CheckoutResultsErrorResponse Order;
  private CheckoutResultsErrorResponse DeliveryPackagingPreferences;
  private CheckoutResultsErrorResponse FulfilmentWindows;
  private CheckoutResultsErrorResponse PickupStores;
  private CheckoutResultsErrorResponse SetPackagingOption;
}
