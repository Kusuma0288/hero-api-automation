package au.com.woolworths.model.metis.preferenceDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Button {
  private String label;
  private String url;
}
