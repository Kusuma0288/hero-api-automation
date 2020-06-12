package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ApigeeFreeTextItem {
  private long id;
  private String text;
  private long timestamp;
  private boolean checked;

  @Override
  public String toString() {
    return "ApigeeFreeTextItem{" +
        "id=" + id +
        ", text='" + text + '\'' +
        ", timestamp=" + timestamp +
        ", checked=" + checked +
        '}';
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public boolean isChecked() {
    return checked;
  }

  public void setChecked(boolean checked) {
    this.checked = checked;
  }
}
