package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class GroupResponse {

  private String Id;
  private String DisplayOrder;
  private String Key;
  private String DisplayName;
  private String StartDate;
  private String EndDate;
  private String Description;
  private int ProductCount;
  private String RichRelevanceId;

}
