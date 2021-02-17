package au.com.woolworths.model.metis.card.view_gift_card;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Item {
  private String id;
  private String name;
  private String amount;
  private String subtitle;
  private String logoURL;
}