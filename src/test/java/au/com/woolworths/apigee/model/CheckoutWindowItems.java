package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

public class CheckoutWindowItems {
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String StartTimeRange;
  private CheckoutWindowSlots[] Slots;

  @Override
  public String toString() {
    return "CheckoutWindowItemsResponse {" +
            "StartTimeRange=" + StartTimeRange +
            ", Slots=" + Slots + '}';
  }

  public String getStartTimeRange() {
    return StartTimeRange;
  }

  public void setStartTimeRange(String startTimeRange) {
    StartTimeRange = startTimeRange;
  }

  public CheckoutWindowSlots[] getSlots() {
    return Slots;
  }

  public void setSlots(CheckoutWindowSlots[] slots) {
    Slots = slots;
  }
}
