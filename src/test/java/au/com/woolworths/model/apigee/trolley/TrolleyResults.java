package au.com.woolworths.model.apigee.trolley;

import au.com.woolworths.model.apigee.pickup.ResultStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class TrolleyResults {
  private ResultStatus Order;
  private ResultStatus Trolley;
}
