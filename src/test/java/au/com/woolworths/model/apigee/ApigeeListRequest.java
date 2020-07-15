package au.com.woolworths.model.apigee;

import lombok.Data;

@Data public class ApigeeListRequest {
  private long id;
  private String title;
  private String text;
  private long lastsynced;
  private boolean checked;
  private long timestamp;
}
