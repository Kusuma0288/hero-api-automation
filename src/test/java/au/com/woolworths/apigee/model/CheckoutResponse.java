package au.com.woolworths.apigee.model;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class CheckoutResponse {
  private Order Order;
  private CheckoutPackagingPreferencesResponse[] DeliveryPackagingPreferences;
  private CheckoutFulfilmentWindows[] FulfilmentWindows;
  private CheckoutResultsResponse Results;
}
