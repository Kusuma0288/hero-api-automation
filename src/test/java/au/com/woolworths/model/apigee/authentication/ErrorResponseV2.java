package au.com.woolworths.model.apigee.authentication;

import lombok.Data;

@Data public class ErrorResponseV2 {
  private int httpStatusCode;
  private String errorCode;
  private String errorMessage;
  private Object errorDetail;
}
