package au.com.woolworths.model.scango.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class StepUp {

  private String type;
  private Boolean mandatory;
  private String url;
  private String sessionId;
}
