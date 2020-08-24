package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class GuestLoginRequest {

  private int StoreAddressId;
  private int FulfilmentStoreID;
  private String DeviceAuthToken;
  private int Postcode;
}
