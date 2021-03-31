package au.com.woolworths.model.apigee.lists;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class SwitchDefaultListResponse {
  private String status;
}
