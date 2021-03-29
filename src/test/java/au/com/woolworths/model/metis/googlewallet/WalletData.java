package au.com.woolworths.model.metis.googlewallet;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class WalletData {
  private RewardsDigitalWalletToken rewardsDigitalWalletToken;
}