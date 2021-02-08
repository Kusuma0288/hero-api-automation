
package au.com.woolworths.model.metis.partnerdetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Header {
  private HeaderData data;
  private String type;
}
