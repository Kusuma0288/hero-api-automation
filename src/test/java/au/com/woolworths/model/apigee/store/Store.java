package au.com.woolworths.model.apigee.store;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Store {
  private String Id;
  private double AddressId;
  private String AddressText;
  private String Text;
  private String Name;
}
