package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Promotions {
  private double price;
  private boolean isEDR;
  private String cupUom;
  private int cupSize;
  private double cupPrice;

  @Override
  public String toString() {
    return "promotions{" +
            "Price='" + price + '\'' +
            "IsEDR='" + isEDR + '\'' +
            "CupUom='" + cupUom + '\'' +
            "CupSize='" + cupSize + '\'' +
            "CupPrice='" + cupPrice + '\'' +
            '}';
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public boolean getIsEDR() {
    return isEDR;
  }

  public void setIsEDR(boolean isEDR) {
    this.isEDR = isEDR;
  }

  public String getCupUom() {
    return cupUom;
  }

  public void setCupUom(String cupUom) {
    this.cupUom = cupUom;
  }

  public int getCupSize() {
    return cupSize;
  }

  public void setCupSize(int cupSize) {
    this.cupSize = cupSize;
  }

  public double getCupPrice() {
    return cupPrice;
  }

  public void setCupPrice(double cupPrice) {
    this.cupPrice = cupPrice;
  }
}

