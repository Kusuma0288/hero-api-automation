package au.com.woolworths.model.metis.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ConfigResponse {
  private ConfigData data;
  private String type;
  private String statusCode;
}