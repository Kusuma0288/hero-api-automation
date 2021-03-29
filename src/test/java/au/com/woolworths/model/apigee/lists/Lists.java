package au.com.woolworths.model.apigee.lists;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class Lists {
  private String id;
  private String title;
  private String timestamp;
  private String url;
  private int productCount;
  private String color;
  private String referenceId;
}
