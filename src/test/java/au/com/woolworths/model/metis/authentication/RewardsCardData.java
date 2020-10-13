package au.com.woolworths.model.metis.authentication;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data

public class RewardsCardData {
  private String cardNumber;
  private String cardType;
  private String displayCardNumber;
  private String displayName;
}
