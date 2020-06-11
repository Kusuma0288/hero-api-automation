package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Arrays;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ApigeeV3SearchResponse {

  private Apigeev3SearchProducts[] Products;
  private Apigeev3SearchProducts[] fault;
  private int Product_count;
  private String Nextpage;
  private Object Sortoptions;
  private String FaultString;

  @Override
  public String toString() {
    return "ApigeeV3SearchResponse{" +
            "Products=" + Arrays.toString(Products) +
            ", Product_count=" + Product_count +
            ", Nextpage='" + Nextpage + '\'' +
            ", Sortoptions=" + Sortoptions +
            "fault = " + Arrays.toString(fault) +
            ", faultstring = " + FaultString+
            '}';
  }

  public Apigeev3SearchProducts[] getProducts() {
    return Products;
  }

  public void setProducts(Apigeev3SearchProducts[] products) {
    Products = products;
  }

  public int getProduct_count() {
    return Product_count;
  }

  public void setProduct_count(int product_count) {
    Product_count = product_count;
  }

  public String getNextpage() {
    return Nextpage;
  }

  public void setNextpage(String nextpage) {
    Nextpage = nextpage;
  }

  public Object getSortoptions() {
    return Sortoptions;
  }

  public void setSortoptions(Object sortoptions) {
    Sortoptions = sortoptions;
  }
  public void setfaultString(String faultstring){
    FaultString = faultstring;
  }

  public String getFaultString(){ return FaultString;}
}
