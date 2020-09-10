package au.com.woolworths.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Promotions {
  private double price;
  boolean isEDR;
  private String cupUom;
  private int cupSize;
  private double cupPrice;
}

