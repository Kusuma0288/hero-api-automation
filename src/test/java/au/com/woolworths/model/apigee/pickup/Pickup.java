package au.com.woolworths.model.apigee.pickup;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class Pickup {
  private au.com.woolworths.model.apigee.store.Store Store;
  private au.com.woolworths.model.apigee.delivery.Window Window;
}
