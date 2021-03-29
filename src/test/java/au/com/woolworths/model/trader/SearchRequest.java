package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data

public class SearchRequest {
  private String Term;
  private int PageNumber;
  private int PageSize;
  private String SortOption;
}
