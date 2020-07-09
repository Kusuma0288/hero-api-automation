package au.com.woolworths.model.apigee;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class FulFilmentResponse {
  private Pickup Pickup;
  private FulfilmentMethod Fulfilment;
  private FulFilmentResults Results;
}
