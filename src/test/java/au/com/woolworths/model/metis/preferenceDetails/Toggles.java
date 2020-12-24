package au.com.woolworths.model.metis.preferenceDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Toggles {
  private String title;
  private String subtitle;
  private boolean value;
  private String id;
  private Analytics analytics;
  private String altText;

}
