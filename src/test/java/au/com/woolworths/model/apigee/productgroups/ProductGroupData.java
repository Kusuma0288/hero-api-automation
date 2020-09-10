package au.com.woolworths.model.apigee.productgroups;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ProductGroupData {
  private ProductGroupComponents[] Items;
}



