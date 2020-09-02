package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ResponseStatus {
  private String ErrorCode;
  private String Message;
  private String StackTrace;
  private List<Errors> Errors;
  private Object Meta;

}

