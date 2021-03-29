package au.com.woolworths.model.apigee.delivery;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Data
public class Window {
  private int Id;
  private String DisplayDate;
  private String DisplayTime;
  private String DisplayDeliveryFee;
  private String StartTime;
  private String EndTime;
  private Boolean IsExpress;
}
