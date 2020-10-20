package au.com.woolworths.model.metis.logout;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data

public class Logout {
  private String deviceId;
  private String statusCode;
  private String responseString;
}