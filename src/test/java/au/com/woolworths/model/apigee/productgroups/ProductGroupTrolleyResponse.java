package au.com.woolworths.model.apigee.productgroups;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ProductGroupTrolleyResponse {
  private ProductGroupTrolleyData data;
  private String type;
}



