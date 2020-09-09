package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class SubstitutionPreferences {
  private Boolean Enabled;
  private Boolean ShowWarningMessage;
  private String WarningMessage;

}
