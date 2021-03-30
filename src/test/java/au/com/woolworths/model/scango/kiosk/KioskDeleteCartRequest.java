package au.com.woolworths.model.scango.kiosk;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class KioskDeleteCartRequest {
  private String cartbarcode;
  private String reason;
}
