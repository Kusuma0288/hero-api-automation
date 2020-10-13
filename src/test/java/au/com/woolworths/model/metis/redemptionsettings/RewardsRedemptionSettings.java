
package au.com.woolworths.model.metis.redemptionsettings;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RewardsRedemptionSettings {

  private List<Item> items;
  private TwoFactorAuth twoFactorAuth;

}
