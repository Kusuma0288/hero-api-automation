package au.com.woolworths.model.metis.shorecampaigndetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Header {
  private String body;
  private String credits;
  private String expiry;
  private String imageAlt;
  private String imageUrl;
  private String title;
}
