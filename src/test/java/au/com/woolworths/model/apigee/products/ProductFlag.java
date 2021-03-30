package au.com.woolworths.model.apigee.products;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ProductFlag {
  private boolean liquor;
  private boolean ranged;
  private boolean weighed;
  private boolean weightonline;
  private boolean tobacco;
  private boolean everydaypricecut;
  private boolean pmrestriction;
  private boolean forcollection;
  private boolean fordelivery;
  private boolean meatstockloss;

}
