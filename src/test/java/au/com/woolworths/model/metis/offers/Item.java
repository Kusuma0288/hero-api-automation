package au.com.woolworths.model.metis.offers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Item {
  private Object body;
  private String brandLogoUrl;
  private Object displayAnimation;
  private String displayExpiry;
  private String displayStatus;
  private String image;
  private OfferAnalytics offerAnalytics;
  private String offerId;
  private String offerStatus;
  private List<Object> statusIcons;
  private String subtitle;
  private String summary;
  private Object termsAndConditions;
  private String title;
}
