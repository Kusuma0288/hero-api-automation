package au.com.woolworths.model.metis.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class MessageData {
  private String title;
  private String message;
  private String action;
}



