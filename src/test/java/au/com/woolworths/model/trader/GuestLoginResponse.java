package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class GuestLoginResponse {

  private String LoginResult;
  private String LoginMessage;
  private String AuthToken;
  private String RememberMeToken;
  private Session session;
  private String statusCode;

  //This is for handling error codes
  private String Code;
  private String Message;
  @JsonProperty("Error Response")
  private Object Error_Response;


}
