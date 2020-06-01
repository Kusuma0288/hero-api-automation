package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryFulfilmentV3Response {
  private Delivery delivery;
  private FulfilmentMethod fulfilment;

  @Override
  public String toString() {
    return "DeliveryFulfilmentV3Response {" +
            "Delivery=" + delivery +
            ", Fulfilment=" + fulfilment + '}';
  }

  public Delivery getDelivery() {
    return delivery;
  }

  public void setDelivery(Delivery Delivery) {
    delivery = Delivery;
  }

  public FulfilmentMethod getFulfilment() {
    return fulfilment;
  }

  public void setFulfilment(FulfilmentMethod Fulfilment) {
    fulfilment = Fulfilment;
  }
}
