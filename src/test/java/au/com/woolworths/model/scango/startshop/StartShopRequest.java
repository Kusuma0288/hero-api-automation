package au.com.woolworths.model.scango.startshop;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class StartShopRequest {
  private String taponid;
  private String devicemake;
  private String deviceid;
  private String os;
  private String appversion;
  private Boolean skipwalletvalidation;
}
