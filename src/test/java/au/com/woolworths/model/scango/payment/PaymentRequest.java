package au.com.woolworths.model.scango.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PaymentRequest {
    private String clientReference;
    private List<Payment> payment = null;
}
