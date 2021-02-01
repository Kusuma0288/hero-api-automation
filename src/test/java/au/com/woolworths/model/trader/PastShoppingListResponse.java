package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PastShoppingListResponse {

  private List<PastDeliveredItems> Items;
  private String Page;
  private String PageSize;
  private String TotalItemCount;
  private String Message;

}
