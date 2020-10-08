
package au.com.woolworths.model.metis.redemptionsettings;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Item {
  private String body;
  private List<ConfirmationMessage> confirmationMessages;
  private String icon;
  private Boolean isChangeEnabled;
  private Boolean isSelected;
  private String title;
}
