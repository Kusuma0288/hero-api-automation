package au.com.woolworths.model.scango.login;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class ScanGoRefreshTokenResponse {
  private String access_token;
  private String expires_in;
  private String refresh_token;
  private String statusCode;
}