package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ShopperLoginRequestV2 {
  private final String Email;
  private final String Password;
  private final String GuestTrolleyToken;

}
