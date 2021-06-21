package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class FooterTag {

  private String tagType;
  private String tagContent;
  private Object tagLink;
  private String tagName;
  private String TagContentText;
  private String MultibuyData;
}
