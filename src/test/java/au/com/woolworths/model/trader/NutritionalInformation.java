package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class NutritionalInformation {

  private String name;
  private Values values;
  private String servingSize;
  private String servingsPerPack;
}
