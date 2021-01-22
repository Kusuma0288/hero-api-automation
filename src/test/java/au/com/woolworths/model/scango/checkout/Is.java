package au.com.woolworths.model.scango.checkout;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Is {
    private Boolean enabledigitalpay;
    private Boolean paymentFailLimitExceeded;
    private Boolean transferToPos;
    private Boolean finishTransaction;
}
