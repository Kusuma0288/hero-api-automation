package au.com.woolworths.model.scango.firstore;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RefreshTokenEntity {
  private String uat_refresh_token;
  private long modified;
}
