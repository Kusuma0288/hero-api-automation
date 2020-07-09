package au.com.woolworths.model.apigee;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Data public class ResultStatus {
  private int httpStatusCode;
  private Object errorCode;
  private Object errorDetail;
  private String errorMessage;
  private String method;
  private String function;
}
