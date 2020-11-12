package au.com.woolworths.model.metis.offers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RewardsOffer {
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
  private OfferAnalytics offerAnalytics;
  private Object displayAnimation;
  private String brandLogoUrl;
}