package au.com.woolworths.model.scango.firstore;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Entity {
  @JsonProperty("2701")
  private String _2701;
  @JsonProperty("4033")
  private String _4033;
  @JsonProperty("4034")
  private String _4034;
  @JsonProperty("4808")
  private String _4808;
  @JsonProperty("7985")
  private String _7985;
}
