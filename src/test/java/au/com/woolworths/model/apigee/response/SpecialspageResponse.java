package au.com.woolworths.model.apigee.response;

import au.com.woolworths.model.apigee.products.SpecialsCategories;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data public class SpecialspageResponse {
  private SpecialsCategories[] categories;
}

