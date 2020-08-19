package au.com.woolworths.model.rewards;

import au.com.woolworths.helpers.rewards.deserializers.AuthDeserializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(using = AuthDeserializer.class)
@Data
public class Auth {
  private String refreshToken;
  private Integer refreshTokenExpiresIn;
  private String accessToken;
  private Integer accessTokenExpiresIn;

  public Auth(String refreshToken, Integer refreshTokenExpiresIn, String accessToken, Integer accessTokenExpiresIn) {
    this.refreshToken = refreshToken;
    this.refreshTokenExpiresIn = refreshTokenExpiresIn;
    this.accessToken = accessToken;
    this.accessTokenExpiresIn = accessTokenExpiresIn;
  }
}
