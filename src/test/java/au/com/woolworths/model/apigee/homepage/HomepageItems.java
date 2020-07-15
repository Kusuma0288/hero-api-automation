package au.com.woolworths.model.apigee.homepage;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class HomepageItems {
  private HomepageItemsData data;
  private String type;
}



