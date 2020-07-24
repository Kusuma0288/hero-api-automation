package au.com.woolworths.model.apigee;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data public class Lists {
  private String id;
  private String title;
  private String timestamp;
  private String url;
  private int productCount;
}
