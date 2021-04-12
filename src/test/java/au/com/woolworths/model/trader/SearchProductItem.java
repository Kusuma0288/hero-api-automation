package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class SearchProductItem {
  private String Name;
  private Products[] Products;
  private int ProductCount;
  private String DisplayName;
}
