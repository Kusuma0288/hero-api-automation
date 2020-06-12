package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class ResultStatus {
  private int httpStatusCode;
  private Object errorCode;
  private Object errorDetail;
  private String errorMessage;
  private String method;
  private String function;

  @Override
  public String toString() {
    return "ResultStatus{" +
        "httpStatusCode=" + httpStatusCode +
        ", errorCode=" + errorCode +
        ", errorDetail=" + errorDetail +
        ", errorMessage='" + errorMessage + '\'' +
        ", method='" + method + '\'' +
        ", function='" + function + '\'' +
        '}';
  }

  public int getHttpStatusCode() {
    return httpStatusCode;
  }

  public void setHttpStatusCode(int httpStatusCode) {
    this.httpStatusCode = httpStatusCode;
  }

  public Object getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(Object errorCode) {
    this.errorCode = errorCode;
  }

  public Object getErrorDetail() {
    return errorDetail;
  }

  public void setErrorDetail(Object errorDetail) {
    this.errorDetail = errorDetail;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public String getFunction() {
    return function;
  }

  public void setFunction(String function) {
    this.function = function;
  }
}
