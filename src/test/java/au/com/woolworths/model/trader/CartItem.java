package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CartItem {

  private final String stockCode;
  private final int quantity;
  private final String name;
  private final String status;
  private final String note;

}
