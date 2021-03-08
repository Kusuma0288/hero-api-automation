package au.com.woolworths.model.metis.card.view_sc_payment_preferences;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class SCItem {
  private String id;
  private String logoURL;
  private String title;
  private String cardNumber;
  private String status;
  private String isPrimary;
}
