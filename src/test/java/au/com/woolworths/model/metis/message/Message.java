package au.com.woolworths.model.metis.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Message {
  private String icon;
  private String title;
  private String message;
}