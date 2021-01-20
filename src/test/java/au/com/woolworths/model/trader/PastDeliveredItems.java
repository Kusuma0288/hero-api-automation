package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PastDeliveredItems {
  private String Id;
  private String DeliveryDate;
  private String DeliveryDateString;

}
