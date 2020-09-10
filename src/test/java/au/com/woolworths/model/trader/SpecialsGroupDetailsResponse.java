package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class SpecialsGroupDetailsResponse {

  GroupProductBundles[] Bundles;
  ArrayList<Object> Aggregations = new ArrayList<Object>();
  private int TotalRecordCount;
  private Object RichRelevancePlacement;
  private boolean HasRewardsCard;
  private boolean HasTobaccoItems;
  private boolean Success;
  private String SpecialsGroupId;

}
