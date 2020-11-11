package au.com.woolworths.model.metis.redemptionsettings;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

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
