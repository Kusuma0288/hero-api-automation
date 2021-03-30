package au.com.woolworths.model.apigee.address;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Data
public class Address {
  private Integer Id;
  private String Text;
}

