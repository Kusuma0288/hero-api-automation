package au.com.woolworths.model.rewards;

import au.com.woolworths.helpers.rewards.deserializers.RewardsCardDeserializer;
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

  public RewardsCard(String cardNumber, String displayCardNumber, String displayName, String cardType) {
    this.cardNumber = cardNumber;
    this.displayCardNumber = displayCardNumber;
    this.displayName = displayName;
    this.cardType = cardType;
  }
}
