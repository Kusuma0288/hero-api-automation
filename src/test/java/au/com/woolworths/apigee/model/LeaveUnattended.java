package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class LeaveUnattended {
  private boolean ShowLeaveUnattended;
  private boolean DisableLeaveUnattended;
  private boolean CanLeaveUnattended;
  private String WarningMessage;

}
