package au.com.woolworths.model.scango.firstore;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UpsertFirestoreDocRefreshTokenRequest {
  private String documentId;
  private String uat_refresh_token;
}
