package au.com.woolworths.model.metis.preferenceDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PreferenceDetails {
  private List<Details> details;
  private String title;
}
