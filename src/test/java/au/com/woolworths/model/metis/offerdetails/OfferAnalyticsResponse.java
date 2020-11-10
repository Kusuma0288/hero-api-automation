package au.com.woolworths.model.metis.offerdetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class OfferAnalyticsResponse {
  private String commId;
  private String uoid;
  private String campaignCode;
  private String campaignStream;
  private String channel;
  private String status;
}