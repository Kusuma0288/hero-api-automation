package au.com.woolworths.model.scango.startshop;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserProfileStoreInfo {
  private String no;
  private String name;
  private String checkouttitle;
  private String checkoutmessage;
}
