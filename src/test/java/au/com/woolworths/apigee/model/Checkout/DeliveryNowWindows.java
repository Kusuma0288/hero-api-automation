package au.com.woolworths.apigee.model.Checkout;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class DeliveryNowWindows {

  private String Title;
  private String SubTitle;
  private int Id;
  private Object Info;
  private String StartTime;
  private String DisplayPrice;
  private boolean IsReserved;
}
