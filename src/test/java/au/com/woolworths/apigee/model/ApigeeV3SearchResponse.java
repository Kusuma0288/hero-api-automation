package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ApigeeV3SearchResponse {

  private Apigeev3SearchProducts[] products;
  private Apigeev3SearchProducts[] fault;
  private int productCount;
  private String nextPage;
  private Object sortOptions;
  private String faultString;

  @Override
  public String toString() {
    return "ApigeeV3SearchResponse{" + "products=" + Arrays.toString(products) + ", ProductCount=" + productCount + ", nextPage='" + nextPage + '\'' + ", sortOptions=" + sortOptions + "fault = " + Arrays.toString(fault) + ", faultString = " + faultString + '}';
  }

  public Apigeev3SearchProducts[] getProducts() {
    return products;
  }

  @JsonProperty("product_count")
  public void setProducts(Apigeev3SearchProducts[] product) {
    products = product;
  }

  public int getProductCount() {
    return productCount;
  }

  @JsonProperty("product_count")
  public void setProductCount(int product_count) {
    productCount = product_count;
  }

  public String getNextpage() {
    return nextPage;
  }

  public void setNextpage(String nextpage) {
    nextPage = nextpage;
  }

  public Object getSortoptions() {
    return sortOptions;
  }

  public void setSortoptions(Object sortoptions) {
    sortOptions = sortoptions;
  }

  public void setfaultString(String faultstring) {
    faultString = faultstring;
  }

  public String getFaultString() {
    return faultString;
  }
}