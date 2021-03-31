package au.com.woolworths.model.apigee.trolley;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class TrolleyDetails {
  private double subtotalInclDelivery;
  private int savingsOnTrolley;
  private int totalProducts;
  private int deliveryFee;
  private List<TrolleyItemsListResponse> items;
  private Object wowRewardsSummary;
  private Object loyalty;

}
