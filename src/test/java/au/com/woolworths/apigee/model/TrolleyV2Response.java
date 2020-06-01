package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Arrays;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrolleyV2Response {
  private int totaltrolleyprice;
  private int savingsontrolley;
  private int totalproducts;
  private int deliveryfee;
  private List<TrolleyItemsListResponse> items;
  private Object wowrewardssummary;
  private Object loyalty;
  private Object[] errors;

  @Override
  public String toString() {
    return "TrolleyV2Response{" +
            "totaltrolleyprice=" + totaltrolleyprice +
            ", savingsontrolley=" + savingsontrolley +
            ", totalproducts=" + totalproducts +
            ", deliveryfee=" + deliveryfee +
            ", items=" + items +
            ", wowrewardssummary=" + wowrewardssummary +
            ", loyalty=" + loyalty +
            ", errors=" + Arrays.toString(errors) +
            '}';
  }

  public int getTotaltrolleyprice() {
    return totaltrolleyprice;
  }

  public void setTotaltrolleyprice(int totaltrolleyprice) {
    this.totaltrolleyprice = totaltrolleyprice;
  }

  public int getSavingsontrolley() {
    return savingsontrolley;
  }

  public void setSavingsontrolley(int savingsontrolley) {
    this.savingsontrolley = savingsontrolley;
  }

  public int getTotalproducts() {
    return totalproducts;
  }

  public void setTotalproducts(int totalproducts) {
    this.totalproducts = totalproducts;
  }

  public int getDeliveryfee() {
    return deliveryfee;
  }

  public void setDeliveryfee(int deliveryfee) {
    this.deliveryfee = deliveryfee;
  }

  public List<TrolleyItemsListResponse> getItems() {
    return items;
  }

  public void setItems(List<TrolleyItemsListResponse> items) {
    this.items = items;
  }

  public Object getWowrewardssummary() {
    return wowrewardssummary;
  }

  public void setWowrewardssummary(Object wowrewardssummary) {
    this.wowrewardssummary = wowrewardssummary;
  }

  public Object getLoyalty() {
    return loyalty;
  }

  public void setLoyalty(Object loyalty) {
    this.loyalty = loyalty;
  }

  public Object[] getErrors() {
    return errors;
  }

  public void setErrors(Object[] errors) {
    this.errors = errors;
  }
}
