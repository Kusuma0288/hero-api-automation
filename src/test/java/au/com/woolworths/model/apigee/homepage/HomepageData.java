package au.com.woolworths.model.apigee.homepage;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class HomepageData {
  private HomepageComponents[] Items;
}



