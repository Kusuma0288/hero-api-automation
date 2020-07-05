package au.com.woolworths.apigee.model;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class PickUpType {
  private long AddressId;
  private String Type;
  private String Label;
  private String Text;
}
