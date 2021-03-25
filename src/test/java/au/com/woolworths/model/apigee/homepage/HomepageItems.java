package au.com.woolworths.model.apigee.homepage;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class HomepageItems {
  private HomepageItemsData data;
  private String type;
}



