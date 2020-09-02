package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PickupRequest {

  private String DeliveryMethod;
  private String AddressId;
  private String Description;

}
