package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CheckoutV3DeliveryWindows {

  private String Date;
  private String DateText;
  private boolean Available;
  private CheckoutV3DeliverySlots[] Slots;
  private boolean IsExpressDeliveryExhausted;
  private String AllWindowsClosedMessage;
  private String ExpressWindowClosedText;
}
