package au.com.woolworths.model.scango.scanitems;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Is {
  private Boolean quantityupdateallowed;
  private Boolean liquor;
  private Boolean ranged;
  private Boolean weighed;
  private Boolean weightonline;
  private Boolean tobacco;
  private Boolean pmrestriction;
  private Boolean priceembedded;
  private Boolean forcedquantity;
  private Boolean weightrequired;
  private Boolean intervention;
  private Boolean assistancerequired;
  private Boolean unknownitem;
  private Boolean simpleproduct;
  private Boolean tun;
  private Boolean reducedtoclear;
  private Boolean promotionalitem;
  private Boolean linkedpromo;
  private Boolean taxeditem;
}
