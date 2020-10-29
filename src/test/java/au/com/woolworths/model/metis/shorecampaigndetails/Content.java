package au.com.woolworths.model.metis.shorecampaigndetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Content {
  private String credits;
  private String heading;
  private String body;
  private String expiry;
}
