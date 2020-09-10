package au.com.woolworths.model.apigee.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class iFrameRequest {
  public String aa;
  public String bb;
  public String dd;
  public String ee;
  public boolean save;
  public boolean verify;
  public boolean primary;
}
