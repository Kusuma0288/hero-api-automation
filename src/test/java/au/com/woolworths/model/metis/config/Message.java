package au.com.woolworths.model.metis.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Message {
  private MessageData data;
  private String type;
}