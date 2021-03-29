package au.com.woolworths.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Promotions {
  boolean isEDR;
  private double price;
  private String cupUom;
  private int cupSize;
  private double cupPrice;
}

