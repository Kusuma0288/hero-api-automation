package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class WeeklyPicksResponse {

  private List<Bundle> bundles = null;
  private Integer totalRecordCount;
  private Boolean hasRewardsCard;
  private Boolean hasTobaccoItems;
  private Boolean success;
  private String statusCode;

}
