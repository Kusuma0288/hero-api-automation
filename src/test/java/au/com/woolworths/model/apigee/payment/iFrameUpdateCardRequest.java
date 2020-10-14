package au.com.woolworths.model.apigee.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
public class iFrameUpdateCardRequest {
  public Authentication authentication;
  private String cvv;
  private String expiryMonth;
  private String expiryYear;
  private String itemId;
  private String scheme;
}