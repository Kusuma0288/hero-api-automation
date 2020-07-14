package au.com.woolworths.model.apigee.productgroups;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class ProductGroupResponse {
  private ProductGroupData data;
  private String type;
}



