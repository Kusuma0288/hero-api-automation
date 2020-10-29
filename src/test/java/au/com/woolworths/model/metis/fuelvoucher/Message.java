
package au.com.woolworths.model.metis.fuelvoucher;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Message {
  private Button button;
  private String message;
  private String title;
  private String iconUrl;
}
