package au.com.woolworths.model.apigee.checkout;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class CheckoutWindowItems {
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String StartTimeRange;
  private CheckoutWindowSlots[] Slots;
}
