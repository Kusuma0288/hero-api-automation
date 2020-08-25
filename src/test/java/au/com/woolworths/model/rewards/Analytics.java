package au.com.woolworths.model.rewards;

import au.com.woolworths.helpers.rewards.deserializers.AnalyticsDeserializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(using = AnalyticsDeserializer.class)
@Data
public class Analytics {
  private String visitorId;
  private String type;

  public Analytics(String visitorId, String type) {
    this.visitorId = visitorId;
    this.type = type;
  }
}
