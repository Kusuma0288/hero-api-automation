package au.com.woolworths.model.metis.discover;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RewardsDiscoverResponse {
  private RewardsDiscoverData data;
}
