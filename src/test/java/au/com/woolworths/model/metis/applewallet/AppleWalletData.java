package au.com.woolworths.model.metis.applewallet;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AppleWalletData {
    private WalletPkPass walletPkPass;
}