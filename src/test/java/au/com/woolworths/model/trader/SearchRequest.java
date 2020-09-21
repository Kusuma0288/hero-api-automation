package au.com.woolworths.model.trader;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data

public class SearchRequest {
  private String Term;
  private int PageNumber;
  private int PageSize;
  private String SortOption;
}
