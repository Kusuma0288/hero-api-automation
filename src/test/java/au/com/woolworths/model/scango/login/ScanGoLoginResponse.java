package au.com.woolworths.model.scango.login;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class ScanGoLoginResponse {
    private String access_token;
    private String expires_in;
    private String refresh_token;
    private String refresh_token_expires_in;
    private String email;
    private String rewardscardNumber;
    private String firstname;
    private String lastname;
    private Boolean istncaccepted;
    private Boolean ispaymentadded;
    private Boolean isrewardsavailable;
    private String banner;
    private String statusCode;
}
