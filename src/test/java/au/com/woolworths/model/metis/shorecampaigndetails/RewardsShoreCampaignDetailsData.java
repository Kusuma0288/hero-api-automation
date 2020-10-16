package au.com.woolworths.model.metis.shorecampaigndetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RewardsShoreCampaignDetailsData {
  private RewardsShoreCampaignDetails rewardsShoreCampaignDetails;
}
