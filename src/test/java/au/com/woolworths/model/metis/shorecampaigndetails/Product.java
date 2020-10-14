package au.com.woolworths.model.metis.shorecampaigndetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Product {
  private String description;
  private String imageAlt;
  private String imageUrl;
  private String price;
  private RedemptionStatus redemptionStatus;
}
