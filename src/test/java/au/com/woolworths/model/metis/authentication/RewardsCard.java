package au.com.woolworths.model.metis.authentication;

import au.com.woolworths.helpers.metis.deserializers.authentication.RewardsCardDeserializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(using = RewardsCardDeserializer.class)
@Data
public class RewardsCard {
  private String cardNumber;
  private String displayCardNumber;
  private String displayName;
  private String cardType;
  private String type;

  public RewardsCard(String cardNumber, String displayCardNumber, String displayName, String cardType, String type) {
    this.cardNumber = cardNumber;
    this.displayCardNumber = displayCardNumber;
    this.displayName = displayName;
    this.cardType = cardType;
    this.type = type;
  }
}
