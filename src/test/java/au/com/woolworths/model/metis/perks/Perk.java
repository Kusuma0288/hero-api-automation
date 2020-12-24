package au.com.woolworths.model.metis.perks;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Perk {
  private Brand brand;
  private Button button;
  private String cardImageUrl;
  private String description;
  private String headerImageUrl;
  private String partnerName;
  private TermsAndConditions termsAndConditions;
  private String title;
}
