package au.com.woolworths.apigee.model;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class InStorePrice {

  private String cupuom;
  private int cupsize;
  private String uom;
  private double pricegst;
  private double cupprice;
  private String previousprice;
}
