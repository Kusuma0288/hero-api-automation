package au.com.woolworths.model.metis.perks;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RewardsPerks {
  private Banner banner;
  private List<Perk> perks;
  private String title;
}
