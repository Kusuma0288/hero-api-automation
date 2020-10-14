package au.com.woolworths.model.metis.shorecampaigndetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Footer {
  private Button button;
  private String imageAlt;
  private String imageUrl;
  private String termsAndConditions;
  private String title;
}
