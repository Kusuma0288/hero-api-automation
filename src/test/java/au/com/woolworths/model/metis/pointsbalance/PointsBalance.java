package au.com.woolworths.model.metis.pointsbalance;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data

public class PointsBalance {
  private int pointsBalance;
  private Object redemptionPercentage;
  private String displayMessage;
  private String statusMarkUrl;
  private boolean showStatusMark;
}