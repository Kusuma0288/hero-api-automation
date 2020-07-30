package au.com.woolworths.model.apigee.products;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class IsProductFlag {
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
