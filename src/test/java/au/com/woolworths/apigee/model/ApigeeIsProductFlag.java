package au.com.woolworths.apigee.model;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class ApigeeIsProductFlag {
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
