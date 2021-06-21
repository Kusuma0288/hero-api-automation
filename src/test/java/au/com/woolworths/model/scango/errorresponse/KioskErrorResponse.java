package au.com.woolworths.model.scango.errorresponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class KioskErrorResponse {
  private Integer statusCode;
  private Integer errorCode;
  private String message;
  private String description;
}
