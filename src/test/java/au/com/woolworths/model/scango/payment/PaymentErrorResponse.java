package au.com.woolworths.model.scango.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PaymentErrorResponse {
    private Integer statusCode;
    private String errorCode;
    private String message;
    private String description;
    private String digitalPayDescp;
    private Boolean paymentFailLimitExceeded;
}
