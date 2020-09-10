package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ShopperLoginResponseV2 {

  private String LoginResult;
  private String LoginMessage;
  private String AuthToken;
  private String RefreshToken;
  private Session Session;
  private String TrolleyMergedMessage;
  private ResponseStatus ResponseStatus;
  private Object SaveForLater;
  private String IdmAccessToken;
  private String IdmAccessTokenExpiresIn;
  private String IdmRefreshToken;
  private String IdmRefreshTokenExpiresIn;
  private String IdmTokensIssuedAt;
  private String statusCode;
  private String Message;

}
