package au.com.woolworths.model.scango.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ListInstrumentsResponse {
  private List<CreditCard> creditCards = null;
  private List<Object> giftCards = null;
  private List<Object> payPal = null;
  private List<Object> paymentAgreements = null;
  private Object androidPay;
  private Object googlePay;
  private Object applePay;
  private String statusCode;
}
