package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Loyalty {
  private String Points;
  private Object SaveForLaterPreference;
  private boolean RedeemNowEnabled;
  private Object PayOutDate;

}