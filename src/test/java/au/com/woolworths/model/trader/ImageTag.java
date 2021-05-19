package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class ImageTag {

  private String tagType;
  private String tagContent;
  private Object tagLink;
  private String tagName;
  private String TagContentText;
}
