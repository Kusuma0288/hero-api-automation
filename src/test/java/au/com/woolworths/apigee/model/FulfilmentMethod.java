package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FulfilmentMethod {
  private String Method;
  private String Label;

  @Override
  public String toString() {
    return "FulfilmentMethod{" +
        "Method='" + Method + '\'' +
        ", Label='" + Label + '\'' +
        '}';
  }

  public String getMethod() {
    return Method;
  }

  public void setMethod(String method) {
    Method = method;
  }

  public String getLabel() {
    return Label;
  }

  public void setLabel(String label) {
    Label = label;
  }
}
