package au.com.woolworths.model.metis.card;

import lombok.Data;

@Data
public class Balance {

  private RedemptionSettings redemptionSettings;
  private FuelVouchers fuelVouchers;
  private String displayName;
}