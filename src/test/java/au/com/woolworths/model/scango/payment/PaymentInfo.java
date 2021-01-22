package au.com.woolworths.model.scango.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PaymentInfo {
    private String cardSuffix;
    private String scheme;
    private Double amount;
    private String paymentInstrumentType;
}
