package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Errors {

  private String ErrorCode;
  private String Key;
  private String FieldName;
  private String Message;
  private String StockCode;

}

