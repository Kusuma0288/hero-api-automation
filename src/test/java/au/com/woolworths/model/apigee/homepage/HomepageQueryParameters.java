package au.com.woolworths.model.apigee.homepage;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class HomepageQueryParameters {

  private String groupId;

}