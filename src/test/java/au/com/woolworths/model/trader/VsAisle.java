package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class VsAisle {

  private String name;
  private String urlFriendlyName;
  private List<VsCategory> vsCategories = null;
  private String richRelevanceId;

}
