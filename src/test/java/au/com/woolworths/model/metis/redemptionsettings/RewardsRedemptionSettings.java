package au.com.woolworths.model.metis.redemptionsettings;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RewardsRedemptionSettings {

  private List<Item> items;
  private TwoFactorAuth twoFactorAuth;

}
