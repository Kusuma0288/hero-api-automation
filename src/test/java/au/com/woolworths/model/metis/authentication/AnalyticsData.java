package au.com.woolworths.model.metis.authentication;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data

public class AnalyticsData {
  private String visitorId;
}
