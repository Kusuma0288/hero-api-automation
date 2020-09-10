package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AddressDetails {

  private Address Address;
  private boolean IsNonServiced;
  private Object ResponseStatus;
  private String Code;
  private String Message;
  private String StatusCode;
  @JsonProperty("Error Response")
  private Object ErrorResponse;
  private Object ErrorMessage;

}
