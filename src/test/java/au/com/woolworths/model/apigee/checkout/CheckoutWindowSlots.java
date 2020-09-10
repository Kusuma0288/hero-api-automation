package au.com.woolworths.model.apigee.checkout;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@Data
public class CheckoutWindowSlots {
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private int Id;
  private String StartTime;
  private String EndTime;
  private String Status;
  private String StatusText;
  private int BasePrice;
  private int Discount;
  private int SalePrice;
  private String DisplayPrice;
  private boolean IsAvailable;
  private boolean IsReserved;
  private String Duration;
  private Object BadgeLabel;
}
