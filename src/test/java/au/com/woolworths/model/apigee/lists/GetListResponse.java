package au.com.woolworths.model.apigee.lists;

import au.com.woolworths.model.apigee.lists.Lists;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data public class GetListResponse {
  private Lists[] lists;
}
