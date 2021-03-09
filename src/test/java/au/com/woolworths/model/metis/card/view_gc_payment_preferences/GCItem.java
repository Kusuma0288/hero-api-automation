package au.com.woolworths.model.metis.card.view_gc_payment_preferences;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class GCItem {
  private String logoURL;
  private String name;
  private Boolean useFirst;
  private Boolean disabled;
}
