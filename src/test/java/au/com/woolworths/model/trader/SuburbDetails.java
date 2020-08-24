package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class SuburbDetails {
  private String PostCode;
  private String SuburbName;
  private String State;
  private String SuburbStateString;

}
