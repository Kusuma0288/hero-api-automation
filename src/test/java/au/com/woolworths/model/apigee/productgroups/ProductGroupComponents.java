package au.com.woolworths.model.apigee.productgroups;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class ProductGroupComponents {
  private ProductGroupComponentsData data;
  private String type;
}



