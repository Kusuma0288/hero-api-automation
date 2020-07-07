package au.com.woolworths.apigee.model.Trolley;

import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class TrolleyDetails {
  private double subtotalInclDelivery;
  private int savingsOnTrolley;
  private int totalProducts;
  private int deliveryFee;
  private List<TrolleyItemsListResponse> items;
  private Object wowRewardsSummary;
  private Object loyalty;

}
