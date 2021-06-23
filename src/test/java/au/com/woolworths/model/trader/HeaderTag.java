package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class HeaderTag {

  private String BackgroundColor;
  private String BorderColor;
  private String TextColor;
  private String Content;
  private Object TagLink;
  private String Promotion;
}
