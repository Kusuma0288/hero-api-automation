package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class VsCategory {

  private String name;
  private String urlFriendlyName;
  private Boolean isRestricted;
  private Integer productCount;
  private String richRelevanceId;


}
