
package au.com.woolworths.model.metis.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Option {
  private String typename;
  private String id;
  private String title;
  private String url;
  private String page;
  private Boolean shouldShowNewBadge;
  private Action action;
}
