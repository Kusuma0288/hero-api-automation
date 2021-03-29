package au.com.woolworths.model.apigee.fulfilment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class FulfilmentMethod {
  private String Method;
  private String Label;
}
