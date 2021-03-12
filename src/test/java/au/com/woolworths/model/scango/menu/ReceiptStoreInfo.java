package au.com.woolworths.model.scango.menu;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ReceiptStoreInfo {
  private String name;
  private String id;
  private Integer tillid;
  private String address;
  private String division;
  private String phone;
}
