
package au.com.woolworths.model.metis.partnerdetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PartnerDetailsResponse {
  private PartnerDetailsData data;
  private String type;
  private String statusCode;
}
