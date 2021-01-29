package au.com.woolworths.model.metis.partnerdetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PartnerTradingHoursData {
  private String day;
  private String openHours;
}
