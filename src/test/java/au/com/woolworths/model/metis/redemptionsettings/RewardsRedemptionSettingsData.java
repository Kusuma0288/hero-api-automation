
package au.com.woolworths.model.metis.redemptionsettings;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RewardsRedemptionSettingsData {

  private RewardsRedemptionSettings rewardsRedemptionSettings;

}
