package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CheckoutAddressResponse {
  private boolean IsServiceNotAvailable;
  private boolean IsSuccessful;
  private Address Address;
  private String DeliveryMethod;
  private Object ResponseStatus;
  private String FulfilmentMethod;
  private String Code;
  private String Message;

}
