package au.com.woolworths.model.scango.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Payment {
    private String paymentInstrumentId;
    private Double amount;
    private String cardSuffix;
    private String cardType;
}
