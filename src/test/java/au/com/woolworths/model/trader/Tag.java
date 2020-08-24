package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Tag {
  private String TagType;
  private String TagContent;
  private String TagLink;
  private String TagName;

}
