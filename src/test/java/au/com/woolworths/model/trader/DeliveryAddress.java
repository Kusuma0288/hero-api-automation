package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class DeliveryAddress {
  private String AddressStreet1;
  private String AddressSuburb;
  private String AddressPostcode;

}
