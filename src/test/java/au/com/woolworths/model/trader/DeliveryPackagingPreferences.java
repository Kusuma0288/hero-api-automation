package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class DeliveryPackagingPreferences {
  private int Id;
  private String Code;
  private String Name;
  private int Cost;
  private boolean IsSelected;
  private String Image;
  private String Description;
  private String FeeLabel;
  private boolean IsFlatFee;
  private boolean IsNew;
  private int UnitCost;
  private int EstimatedCount;


}
