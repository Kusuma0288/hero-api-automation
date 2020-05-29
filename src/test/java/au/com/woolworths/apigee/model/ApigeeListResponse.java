package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ApigeeListResponse {
  private ApigeeListDetailsResponse update;
  private Object changes;

  @Override
  public String toString() {
    return "ApigeeListResponse{" +
            "update='" + update + '\'' +
            ", changes='" + changes + '\'' +
            '}';
  }

  public ApigeeListDetailsResponse getUpdate() {
    return update;
  }

  public void setUpdate(ApigeeListDetailsResponse update) {
    this.update = update;
  }

  public Object getChanges() {
    return changes;
  }

  public void setChanges(Object changes) {
    this.changes = changes;
  }
}