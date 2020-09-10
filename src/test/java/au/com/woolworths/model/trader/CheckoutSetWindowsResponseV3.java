package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CheckoutSetWindowsResponseV3 {
  public boolean IsSuccessful;
  public String Message;
  public boolean IsNonServiced;
}
