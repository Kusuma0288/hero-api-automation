package au.com.woolworths.model.apigee.address;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class SearchAddressDetails {

  private String AmasID;
  private String Text;
  private String PostCode;
}
