package au.com.woolworths.apigee.model.ProductGroups;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data public class ProductGroupTrolleyData {
  private String minimum;
  private String maximum;
  private String increment;
  private String defaultInTrolley;
  private String inTrolley;
  private String unit;
  private String buttonState;
  private String buttonLabel;
  private String buttonQuantity;
}



