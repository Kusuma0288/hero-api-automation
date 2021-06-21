package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CentreTag {

  private String tagType;
  private Object tagContent;
  private Object tagLink;
  private Object tagName;
  private String TagContentText;
  private String MultibuyData;
}
