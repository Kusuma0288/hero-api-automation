package au.com.woolworths.apigee.model;

public class Fulfilmentv3ErrorResponse {
  private int httpStatusCode;
  private String errorCode;
  private String errorMessage;
  private Object errorDetail;

  @Override
  public String toString() {
    return "Fulfilmentv3ErrorResponse{" +
        "httpStatusCode=" + httpStatusCode +
        ", errorCode=" + errorCode +
        ", errorMessage=" + errorMessage +
        ", errorDetail=" + errorDetail +
        '}';
  }

  public int getHttpStatusCode() {
    return httpStatusCode;
  }

  public void setHttpStatusCode(int HttpStatusCode) {
    httpStatusCode = HttpStatusCode;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String ErrorCode) {
    errorCode = ErrorCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String ErrorMessage) {
    errorMessage = ErrorMessage;
  }

  public Object getErrorDetail() {
    return errorDetail;
  }

  public void setErrorDetail(Object ErrorDetail) {
    errorDetail = errorDetail;
  }
}
