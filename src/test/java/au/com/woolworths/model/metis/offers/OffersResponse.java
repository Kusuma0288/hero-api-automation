package au.com.woolworths.model.metis.offers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class OffersResponse {
  private OffersData data;
}
