package au.com.woolworths.apigee.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrolleyDetails {
  private double subtotalInclDelivery;
  private int savingsOnTrolley;
  private int totalProducts;
  private int deliveryFee;
  private List<TrolleyItemsListResponse> items;
  private Object wowRewardsSummary;
  private Object loyalty;

  @Override
  public String toString() {
    return "TrolleyResults{" +
        "SubtotalInclDelivery=" + subtotalInclDelivery +
        ", SavingsOnTrolley=" + savingsOnTrolley +
        ", TotalProducts=" + totalProducts +
        ", DeliveryFee=" + deliveryFee +
        ",items=" + items +
        ",WowRewardsSummary=" + wowRewardsSummary +
        ",Loyalty=" + loyalty +
        '}';
  }

  public double getSubtotalInclDelivery() {
    return subtotalInclDelivery;
  }

  public void setSubtotalInclDelivery(double subtotalInclDelivery) {
    this.subtotalInclDelivery = subtotalInclDelivery;
  }

  public int getSavingsOnTrolley() {
    return savingsOnTrolley;
  }

  public void setSavingsOnTrolley(int savingsOnTrolley) {
    this.savingsOnTrolley = savingsOnTrolley;
  }

  public int getTotalProducts() {
    return totalProducts;
  }

  public void setTotalProducts(int totalProducts) {
    this.totalProducts = totalProducts;
  }

  public int getDeliveryFee() {
    return deliveryFee;
  }

  public void setDeliveryFee(int deliveryFee) {
    this.deliveryFee = deliveryFee;
  }

  public List<TrolleyItemsListResponse> getTrolleyitemsListResp() {
    return items;
  }

  public void setTrolleyitemsListResp(List<TrolleyItemsListResponse> trolleyitemsListResp) {
    this.items = trolleyitemsListResp;
  }

  public Object getWowRewardsSummary() {
    return wowRewardsSummary;
  }

  public Object getItems() {
    return items;
  }

  public void setWowRewardsSummary(Object wowRewardsSummary) {
    this.wowRewardsSummary = wowRewardsSummary;
  }

  public Object getLoyalty() {
    return loyalty;
  }

  public void setLoyalty(Object loyalty) {
    this.loyalty = loyalty;
  }

}
