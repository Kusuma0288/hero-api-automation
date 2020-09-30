package au.com.woolworths.model.metis.authentication;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class LoginResponseData {
  private Analytics analytics;
  private Auth auth;
  private RewardsCard rewardsCard;
}
