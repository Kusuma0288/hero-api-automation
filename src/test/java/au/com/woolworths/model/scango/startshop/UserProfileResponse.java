package au.com.woolworths.model.scango.startshop;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserProfileResponse {
  private Profile profile;
  private Status status;
  private Preference preference;
  private UserProfileStoreInfo storeinfo;
  private Survey survey;
  private String statusCode;
}
