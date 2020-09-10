package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class GetProductItems {

  private SearchProductItem[] Items;
  private int Page;
  private int PageSize;
  private int TotalItemCount;
  private ResponseStatus ResponseStatus;

}
