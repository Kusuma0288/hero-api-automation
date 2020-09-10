package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CheckoutV3DeliverySlots {
  private Integer Id;
  private String StartTime;
  private String EndTime;
  private String Status;
  private String StatusText;
  private String ExpressDeliveryStatus;
  private float BasePrice;
  private float SalePrice;
  private boolean IsAvailable;
  private boolean IsReserved;
  private boolean IsExpress;
  private boolean IsExclusive;
  private String TimeWindow;
  private String NormalAllocationStatus;
  private Integer ExpressEtaInMinutes;
  private boolean EligibleForDeliverySaver;
  private boolean IsCrowdSourced;
  private boolean IsSecondary;
  private Object DynamicLabel;
}
