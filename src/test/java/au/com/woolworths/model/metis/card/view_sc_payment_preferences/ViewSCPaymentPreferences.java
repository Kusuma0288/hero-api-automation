package au.com.woolworths.model.metis.card.view_sc_payment_preferences;


import lombok.Data;

import java.util.List;

@Data
public class ViewSCPaymentPreferences {
  private String description;
  private Boolean canAddSchemeCard;
  private List<SCItem> items;
}
