package au.com.woolworths.model.scango.startshop;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Profile {
    private String title;
    private String firstname;
    private String lastname;
    private String email;
    private String rewardcardnumber;
    private String phonenumber;
    private String banner;
    private String signin;
}
