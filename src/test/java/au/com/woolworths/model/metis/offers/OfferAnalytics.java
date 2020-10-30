package au.com.woolworths.model.metis.offers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class OfferAnalytics {
  private String campaignCode;
  private String campaignStream;
  private String channel;
  private String commId;
  private String status;
  private String uoid;
}
