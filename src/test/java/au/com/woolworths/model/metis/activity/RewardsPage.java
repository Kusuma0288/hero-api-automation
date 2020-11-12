package au.com.woolworths.model.metis.activity;

import au.com.woolworths.model.metis.message.Message;
import au.com.woolworths.model.metis.pointsbalance.PointsBalance;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data

public class RewardsPage {
  private PointsBalance pointsBalance;
  private ListActivity list;
  private Message message;
}