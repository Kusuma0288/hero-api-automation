package au.com.woolworths.model.apigee.checkout;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)

@Data
public class CheckoutRequest {
  private int window;
  private String date;
  private int packaging;
}
