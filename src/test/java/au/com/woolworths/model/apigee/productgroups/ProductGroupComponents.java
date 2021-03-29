package au.com.woolworths.model.apigee.productgroups;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ProductGroupComponents {
  private ProductGroupComponentsData data;
  private String type;
}



