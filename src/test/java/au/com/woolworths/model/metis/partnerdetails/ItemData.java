package au.com.woolworths.model.metis.partnerdetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ItemData {
  private String title;
  private List<PartnerTradingHoursItem> items = null;
  private String phoneNumber;
  private String displayAddress;
  private List<Facility> facilities = null;
}
