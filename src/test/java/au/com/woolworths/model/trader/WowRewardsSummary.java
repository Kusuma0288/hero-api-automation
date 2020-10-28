package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class WowRewardsSummary {
  private int WowRewardsToSpend;
  private int WowRewardsEarned;
  private boolean IsWowRewardsCardRegistered;
  private boolean IsErrorInGettingWowRewardsBalance;
  private boolean HasValidWowRewardsCard;
  private Object RewardsCredits;
  private boolean RedeemRewardsCredits;

}
