package au.com.woolworths.model.metis.card;

import lombok.Data;

@Data
public class PaymentInstruments {

  private String id;
  private String title;
  private String cardNumber;
  private String logoURL;
  private String status;
  private String lastUsed;
}