package au.com.woolworths.apigee.model;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class ProductGroupTrolleyData {
  private String minimum;
  private String maximum;
  private String increment;
  //TODO rename default to default in Trolley
  @JsonProperty("default")
  private String defaultInTrolley;
  private String inTrolley;
  private String unit;
  private String buttonState;
  private String buttonLabel;
  private String buttonQuantity;
}



