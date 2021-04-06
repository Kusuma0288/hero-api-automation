package au.com.woolworths.model.metis.applewallet;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class AppleWalletData {
  private WalletPkPass walletPkPass;
}