package au.com.woolworths.model.scango.firstore;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UpsertFirestoreDocRefreshResponse {
  private String result;
  private RefreshTokenEntity[] entities = null;
  private String message;
  private String statusCode;
}
