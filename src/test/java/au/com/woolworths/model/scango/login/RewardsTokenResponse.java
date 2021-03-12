package au.com.woolworths.model.scango.login;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RewardsTokenResponse {
  private String id_token;
  private String access_token;
  private String refresh_token;
  private String access_token_expires_in;
  private String refresh_token_expires_in;
}
