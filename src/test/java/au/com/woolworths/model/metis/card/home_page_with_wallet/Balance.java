package au.com.woolworths.model.metis.card.home_page_with_wallet;

import lombok.Data;

@Data
public class Balance {

  private RedemptionSettings redemptionSettings;
  private FuelVouchers fuelVouchers;
  private String displayName;
}