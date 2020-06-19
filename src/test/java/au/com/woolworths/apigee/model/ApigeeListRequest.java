package au.com.woolworths.apigee.model;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data public class ApigeeListRequest {
  private long id;
  private String title;
  private String text;
  private long lastsynced;
  private boolean checked;
  private long timestamp;
}
