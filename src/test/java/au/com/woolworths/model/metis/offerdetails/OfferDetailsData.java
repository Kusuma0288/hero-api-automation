package au.com.woolworths.model.metis.offerdetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class OfferDetailsData {
  private RewardsOfferResponse rewardsOffer;
}