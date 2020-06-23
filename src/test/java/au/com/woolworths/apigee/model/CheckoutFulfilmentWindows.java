package au.com.woolworths.apigee.model;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.org.apache.xpath.internal.operations.Bool;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CheckoutFulfilmentWindows {

  private boolean IsReserved;
  private String StartsFrom;
  private String Date;
  private Boolean IsAvailable;
  private String DateText;
  private boolean IsEligibleForDeliverySaver;
  private CheckoutWindowItems Morning;
  private CheckoutWindowItems Afternoon;
  private CheckoutWindowItems Evening;
  private DeliveryNowWindows DeliveryNow;
}
