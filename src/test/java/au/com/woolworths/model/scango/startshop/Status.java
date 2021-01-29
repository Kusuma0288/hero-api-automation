package au.com.woolworths.model.scango.startshop;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Status {
    private String status;
    private Boolean ispaymentadded;
    private Boolean iswhitelisted;
    private Boolean istncaccepted;
    private Boolean istxnhistory;
    private Boolean isparkingvoucheravailable;
    private Boolean isrewardsavailable;
    private Boolean isskippedrewardsadd;
    private Boolean is2FArequired;
    private Boolean isvalidphoneno;
    private String cartid;
}
