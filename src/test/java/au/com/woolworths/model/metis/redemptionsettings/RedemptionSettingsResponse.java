package au.com.woolworths.model.metis.redemptionsettings;

import au.com.woolworths.helpers.metis.deserializers.redemptionsettings.RedemptionSettingsDeserializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(using = RedemptionSettingsDeserializer.class)
@Data
public class RedemptionSettingsResponse {

  private RewardsRedemptionSettings rewardsRedemptionSettings;

  public RedemptionSettingsResponse(RewardsRedemptionSettings rewardsRedemptionSettings) {
    this.rewardsRedemptionSettings = rewardsRedemptionSettings;
  }
}