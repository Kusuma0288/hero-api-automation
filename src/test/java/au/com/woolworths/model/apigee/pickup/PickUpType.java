package au.com.woolworths.model.apigee.pickup;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PickUpType {
  private long AddressId;
  private String Type;
  private String Label;
  private String Text;
}
