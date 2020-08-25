package au.com.woolworths.model.metis;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class LoginRequest {
  private String authCode;
  private String deviceId;
  private String sessionToken;
}