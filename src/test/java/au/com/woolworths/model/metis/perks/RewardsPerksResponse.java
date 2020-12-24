package au.com.woolworths.model.metis.perks;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RewardsPerksResponse {
  private RewardsPerksData data;
}
