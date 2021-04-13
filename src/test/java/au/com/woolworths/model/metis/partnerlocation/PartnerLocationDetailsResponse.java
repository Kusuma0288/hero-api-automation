package au.com.woolworths.model.metis.partnerlocation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PartnerLocationDetailsResponse {
  private PartnerLocationDetailsData data;
  private String type;
  private String statusCode;
}
