package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class SearchResponse {
  private Object SuggestedTerm;
  private Object[] Products;
  private Object[] Facets;
  private int TotalCount;
}
