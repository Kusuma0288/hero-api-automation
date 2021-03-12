package au.com.woolworths.model.scango.kiosk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class KioskRemoveInterventionResponse {
  private String cartid;
  private String status;
  private KioskIs is;
  private String statusCode;
}
