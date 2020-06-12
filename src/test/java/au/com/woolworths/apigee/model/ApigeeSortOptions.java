package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApigeeSortOptions {
  private String description;
  private Object params;

  @Override
  public String toString() {
    return "ApigeeSortOptions{" +
        "description=" + description +
        "params=" + params +
        +'}';
  }

  public String getDescription() {
    return description;
  }

  public Object getParams() {
    return params;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setParams(Object params) {
    this.params = params;
  }
}
