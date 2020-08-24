package au.com.woolworths.model.trader;

import lombok.Data;

@Data
public class CheckoutWindowSlots {
  private Integer Id;
  private String StartTime;
  private String EndTime;
  private String Status;
  private String StatusText;
  private Integer BasePrice;
  private Integer Discount;
  private Integer Premium;
  private Integer SalePrice;
  private boolean IsAvailable;
  private boolean IsReserved;
}
