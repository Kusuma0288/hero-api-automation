package au.com.woolworths.model.apigee.pickup;

import au.com.woolworths.model.apigee.delivery.Window;
import au.com.woolworths.model.apigee.store.Store;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Pickup {
  private Store Store;
  private Window Window;
}
