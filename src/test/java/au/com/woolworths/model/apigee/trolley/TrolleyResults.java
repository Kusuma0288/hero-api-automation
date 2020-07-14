package au.com.woolworths.model.apigee.trolley;

import au.com.woolworths.model.apigee.ResultStatus;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class TrolleyResults {
  private ResultStatus Order;
  private ResultStatus Trolley;
}
