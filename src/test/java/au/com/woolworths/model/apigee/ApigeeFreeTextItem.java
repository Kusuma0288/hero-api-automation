package au.com.woolworths.model.apigee;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data public class ApigeeFreeTextItem {
  private long id;
  private String text;
  private long timestamp;
  private boolean checked;
}
