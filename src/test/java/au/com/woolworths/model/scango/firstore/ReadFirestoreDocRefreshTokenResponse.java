package au.com.woolworths.model.scango.firstore;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ReadFirestoreDocRefreshTokenResponse {
  private String result;
  private ReadRefreshTokenEntity[] entities;
  private String statusCode;
}
