package au.com.woolworths.model.metis.specials;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PricePointOfferSaving {
  private PricePointOfferSavingData data;
  private String type;
}
