package au.com.woolworths.model.scango.login;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RewardsTokenRequest {
  private String partnersCustomerId;
}