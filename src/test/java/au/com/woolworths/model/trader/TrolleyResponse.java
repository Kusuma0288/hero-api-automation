package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class TrolleyResponse {

  private List<TrolleyItemError> Errors;
  private int Total;
  private int Savings;
  private int TotalProducts;
  private List<Products> Products;
  private Object RestrictedByDeliPlattersProducts;
  private int DeliveryFee;
  private WowRewardsSummary WowRewardsSummary;
  private Object ResponseStatus;
  private Loyalty Loyalty;
  private Boolean HasActiveDeliverySaverSubscription;
  private int DeliveryUnlimitedSubscriptionMinimumOrderAmount;
}
