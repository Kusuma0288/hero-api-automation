package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CheckoutSetWindowsResponseV2 {
  public Boolean IsSuccessful;
  public String Message;
  public Object ResponseStatus;

}
