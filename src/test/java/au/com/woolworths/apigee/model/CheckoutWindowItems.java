package au.com.woolworths.apigee.model;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@Data public class CheckoutWindowItems {
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String StartTimeRange;
  private CheckoutWindowSlots[] Slots;
}
