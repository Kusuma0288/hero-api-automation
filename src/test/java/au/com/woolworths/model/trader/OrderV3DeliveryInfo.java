package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class OrderV3DeliveryInfo {
  private String Street1;
  private String Street2;
  private String Suburb;
  private String PostalCode;
  private String Phone;
  private String CollectionLocationName;
  private String CollectionStoreNumber;
  private String FulfimentStoreId;
}
