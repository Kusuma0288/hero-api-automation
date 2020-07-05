package au.com.woolworths.apigee.model;

import lombok.Data;

@Data public class FulFilmentResults {
  private ResultStatus SetPickupAddress;
  private ResultStatus PickupStores;
}
