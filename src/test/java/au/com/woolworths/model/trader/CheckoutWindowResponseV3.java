package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CheckoutWindowResponseV3 {
  private Integer Id;
  private String WindowDate;
  private String StartTime;
  private String EndTime;
  private Boolean IsAvailable;
  private Integer TimeToCutOff;
  private Boolean IsExpress;
  private Integer ExpressEtaInMinutes;
  private String TimeWindow;
  private boolean IsCrowdSourced;
  private boolean IsSecondary;
  private Object DynamicLabel;

}
