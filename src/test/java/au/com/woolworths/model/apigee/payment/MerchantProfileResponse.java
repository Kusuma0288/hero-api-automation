package au.com.woolworths.model.apigee.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class MerchantProfileResponse {
  private AllowedPaymentMethods allowedPaymentMethods;

  @Data public static class AllowedPaymentMethods {
    private GiftCard giftCard;
    private CreditCard creditCard;
    private Paypal payPal;


    @Data public static class CreditCard {
      private List<String> allowedNetworks;
      private List<String> allowedTransactionTypes;
      private String serviceStatus;
    }
     @Data public static class Paypal {
      private String clientToken;
      private String serviceStatus;
    }
  }

}
