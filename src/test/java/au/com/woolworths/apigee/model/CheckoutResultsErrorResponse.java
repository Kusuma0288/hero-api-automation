package au.com.woolworths.apigee.model;

public class CheckoutResultsErrorResponse {
  private int httpStatusCode;
  private int errorCode;
  private String errorMessage;
  private String errorDetail;
  private String method;
  private String function;

  @Override
  public String toString() {
    return "CheckoutResultsErrorResponse {" +
        "httpStatusCode=" + httpStatusCode +
        ", errorCode=" + errorCode +
        ", errorMessage=" + errorMessage +
        ", errorDetail=" + errorDetail +
        ", method=" + method +
        ", function=" + function +
        '}';
  }

  public int getHttpStatusCode() {
    return httpStatusCode;
  }

  public void setHttpStatusCode(int HttpStatusCode) {
    httpStatusCode = HttpStatusCode;
  }

  public int getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(int ErrorCode) {
    errorCode = ErrorCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String ErrorMessage) {
    errorMessage = ErrorMessage;
  }

  public String getErrorDetail() {
    return errorDetail;
  }

  public void setErrorDetail(String ErrorDetail) {
    errorDetail = ErrorDetail;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String Method) {
    method = Method;
  }

  public String getFunction() {
    return function;
  }

  public void setFunction(String Function) {
    function = Function;
  }
}
