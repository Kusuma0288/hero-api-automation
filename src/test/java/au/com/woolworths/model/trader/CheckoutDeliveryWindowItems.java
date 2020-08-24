package au.com.woolworths.model.trader;

import lombok.Data;

@Data
public class CheckoutDeliveryWindowItems {
  private String Date;
  private String DateText;
  private boolean Available;
  private boolean EligibleForDeliverySaver;
  private CheckoutWindowSlots[] Slots;
}
