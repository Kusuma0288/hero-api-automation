package au.com.woolworths.model.scango.firstore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ReadRefreshTokenEntity {
  private String created;
  private String modified;
  private String prod_refresh_token;
  private String refresh_token;
  private String test_refresh_token;
  private String uat_refresh_token;
  private String uat_additem_refresh_token;
  private String uat_bagcheck_refresh_token;
}
