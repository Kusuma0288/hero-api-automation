package au.com.woolworths.model.apigee.store;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Data
public class StoreInfo {
  private int Id;
  private int AddressId;
  private String Text;
  private String Name;
}
