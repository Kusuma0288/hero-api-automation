package au.com.woolworths.model.trader;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class SearchResponse {
  private Object SuggestedTerm;
  private Object[] Products;
  private Object[] Facets;
  private int TotalCount;
}
