package au.com.woolworths.model.apigee.checkout;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@Data public class CheckoutWindowItems {
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String StartTimeRange;
  private CheckoutWindowSlots[] Slots;
}
