package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApigeeProductDisclaimer {
  private String tobacco;
  private String liquor;

  @Override
  public String toString() {
    return "ApigeeIsProductFlag{" +
        "tobacco=" + tobacco +
        "liquor=" + liquor +
        +'}';
  }

  public String getTobacco() {
    return tobacco;
  }

  public void setTobacco(String tobacco) {
    this.tobacco = tobacco;
  }

  public String getLiquor() {
    return liquor;
  }

  public void setLiquor(String liquor) {
    this.liquor = liquor;
  }
}
