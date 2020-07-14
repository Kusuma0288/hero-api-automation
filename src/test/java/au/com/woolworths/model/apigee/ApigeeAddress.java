package au.com.woolworths.model.apigee;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class ApigeeAddress {

  private String AmasID;
  private String Text;
  private String PostCode;
}
