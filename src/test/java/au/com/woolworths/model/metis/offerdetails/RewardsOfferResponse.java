package au.com.woolworths.model.metis.offerdetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RewardsOfferResponse {
  private String offerId;
  private String image;
  private String title;
  private String subtitle;
  private String summary;
  private String body;
  private String termsAndConditions;
  private String displayExpiry;
  private String displayStatus;
  private String offerStatus;
  private String mechanics;
  private List statusIcons;
  private OfferAnalyticsResponse offerAnalytics;
  private Object displayAnimation;
  private String brandLogoUrl;
}