
package au.com.woolworths.model.metis.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AccountHomePageData {
  private RewardsAccountHomePage rewardsAccountHome;
}