package au.com.woolworths.model.metis.specials;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageData {
  private String imageUrl;
  private String title;
  private String type;
  private String message;
  private ActionButton actionButton;
}
