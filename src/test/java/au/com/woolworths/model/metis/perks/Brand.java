package au.com.woolworths.model.metis.perks;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Brand {
  private String bgColour;
  private String logoUrl;
  private String textColour;
}
