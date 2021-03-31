package au.com.woolworths.model.apigee.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class InStorePrice {

  private String cupuom;
  private int cupsize;
  private String uom;
  private double pricegst;
  private double cupprice;
  private String previousprice;
}
