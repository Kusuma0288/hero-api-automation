package au.com.woolworths.model.metis.card.view_gc_payment_preferences;

import lombok.Data;

import java.util.List;

@Data
public class ViewGCPaymentPreferences {
  private String description;
  private Boolean canAddSchemeCard;
  private List<GCItem> items;
}
