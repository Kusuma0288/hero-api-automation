package au.com.woolworths.model.apigee.fulfilment;

import au.com.woolworths.model.apigee.pickup.ResultStatus;
import lombok.Data;

@Data
public class FulFilmentResults {
  private ResultStatus SetPickupAddress;
  private ResultStatus PickupStores;
}
