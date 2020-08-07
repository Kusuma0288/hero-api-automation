package au.com.woolworths.model.apigee.fulfilment;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class FulfilmentMethod {
  private String Method;
  private String Label;
}
