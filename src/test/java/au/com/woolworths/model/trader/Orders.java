package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Orders {
  private OrderV3 OrderV3;
  private OrderV3DeliveryInfo DeliveryInfo;
  private OrderV3DeliveryWindowInfo DeliveryWindowInfo;
  private Object EtaInfo;
  private String FulfilmentMethod;
  private Boolean IsExpress;
  private String OrderStatus;
}
