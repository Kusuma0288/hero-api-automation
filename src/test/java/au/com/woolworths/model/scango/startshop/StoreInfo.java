package au.com.woolworths.model.scango.startshop;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class StoreInfo {
  private String name;
  private String id;
  private Integer tillid;
  private String address;
  private String division;
  private String phone;
  private String storeType;
  private Boolean isMetro;
  private Boolean notify_wifi;
  private Boolean validateEntryQRCode;
  private Boolean isscangoenabled;
}
