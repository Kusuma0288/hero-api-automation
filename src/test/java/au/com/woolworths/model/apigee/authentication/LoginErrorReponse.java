package au.com.woolworths.model.apigee.authentication;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data public class LoginErrorReponse {
  private String errorCode;
  private String fieldName;
  private String message;
}