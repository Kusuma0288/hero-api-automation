package au.com.woolworths.model.metis.authentication;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RewardsCard {
  private RewardsCardData data;
  private String type;
}
