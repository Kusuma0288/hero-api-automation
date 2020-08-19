package au.com.woolworths.model.rewards;

import au.com.woolworths.helpers.rewards.deserializers.LoginResponseDeserializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(using = LoginResponseDeserializer.class)
@Data
public class LoginResponse {
  private Auth auth;
  private RewardsCard rewardsCard;
  private Analytics rewardsAnalytics;

  public LoginResponse(Auth auth, RewardsCard rewardsCard, Analytics rewardsAnalytics) {
    this.auth = auth;
    this.rewardsCard = rewardsCard;
    this.rewardsAnalytics = rewardsAnalytics;
  }
}