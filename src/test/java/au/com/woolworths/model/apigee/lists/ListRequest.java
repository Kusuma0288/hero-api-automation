package au.com.woolworths.model.apigee.lists;

import lombok.Data;

@Data
public class ListRequest {
  private long id;
  private String title;
  private String text;
  private long lastsynced;
  private boolean checked;
  private long timestamp;
}
