package au.com.woolworths.model.metis.redemptionsettings;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ConfirmationMessage {

  private String buttonLabel;
  private Boolean isDestructive;
  private String message;
  private String title;
}
