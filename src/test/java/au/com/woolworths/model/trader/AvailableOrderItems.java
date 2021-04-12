package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AvailableOrderItems {
  private int Quantity;
  private String Unit;
  private int Subtotal;
  private String Note;
  private Boolean AllowSubstitution;
  private int Stockcode;
  private String Name;
  private String VolumeSize;
  private String ImagePath;
  private Boolean IsAvailable;
  private Boolean IsRestricted;
  private int MinQuantity;
  private int SupplyLimit;
  private int Price;
  private String DisplayName;
}

