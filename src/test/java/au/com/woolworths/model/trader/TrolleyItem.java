package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class TrolleyItem {

  private List<ProductItem> items;
  private boolean ReplaceTrolley;
  private String source;

}
