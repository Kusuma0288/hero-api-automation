package au.com.woolworths.model.scango.kiosk;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class KioskLoginResponse {
    private String accessToken;
    private String expiresIn;
    private String firstname;
    private String fullname;
    private String displayname;
    private String worklocation;
    private Integer storeid;
    private String statusCode;
}
