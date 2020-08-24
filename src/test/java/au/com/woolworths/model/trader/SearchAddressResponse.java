package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class SearchAddressResponse {

  private ResponseStatus ResponseStatus;
  private Items[] items;
  private Object Message;

}
