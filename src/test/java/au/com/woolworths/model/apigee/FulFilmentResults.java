package au.com.woolworths.model.apigee;

import lombok.Data;

@Data public class FulFilmentResults {
  private ResultStatus SetPickupAddress;
  private ResultStatus PickupStores;
}
