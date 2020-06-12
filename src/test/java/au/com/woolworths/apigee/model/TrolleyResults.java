package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrolleyResults {
  private ResultStatus Order;
  private ResultStatus Trolley;

  @Override
  public String toString() {
    return "TrolleyResults{" +
        "Order=" + Order +
        ", Trolley=" + Trolley +
        '}';
  }

  public ResultStatus getOrder() {
    return Order;
  }

  public void setOrder(ResultStatus order) {
    Order = order;
  }

  public ResultStatus getTrolley() {
    return Trolley;
  }

  public void setTrolley(ResultStatus trolley) {
    Trolley = trolley;
  }
}
