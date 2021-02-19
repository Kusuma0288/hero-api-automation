
package au.com.woolworths.model.metis.partnerdetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class HeaderData {
  private String icon;
  private String iconUrl;
  private String title;
  private String content;
  private Button button;
}