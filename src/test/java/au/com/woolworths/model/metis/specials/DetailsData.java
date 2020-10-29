package au.com.woolworths.model.metis.specials;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class DetailsData {
  private String iconUrl;
  private String name;
  private String partnerId;
  private ShopOnlineButton shopOnlineButton;
  private String subtitle;
}
