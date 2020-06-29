package au.com.woolworths.apigee.model.Trolley;

import au.com.woolworths.apigee.model.ResultStatus;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class TrolleyResults {
  private ResultStatus Order;
  private ResultStatus Trolley;
}
