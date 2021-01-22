package au.com.woolworths.model.scango.kiosk;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class VerifyCartResponse {
    private String cartid;
    private String status;
    private KioskIs is;
    private String statusCode;
}
