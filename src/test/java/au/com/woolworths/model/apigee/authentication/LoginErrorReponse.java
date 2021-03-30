package au.com.woolworths.model.apigee.authentication;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class LoginErrorReponse {
  private String errorCode;
  private String fieldName;
  private String message;
}