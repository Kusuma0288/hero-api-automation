package au.com.woolworths.model.metis.preferenceDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Details {
  private String body;
  private String imageUrl;
  private String subtitle;
  private String title;
  private String groupTitle;
  private String description;
  private List<Toggles> toggles;
  private Button button;
}
