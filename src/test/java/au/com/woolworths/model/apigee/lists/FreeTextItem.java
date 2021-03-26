package au.com.woolworths.model.apigee.lists;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class FreeTextItem {
  private long id;
  private String text;
  private long timestamp;
  private boolean checked;
  private String referenceId;
}
