package au.com.woolworths.model.metis.authentication;

import au.com.woolworths.helpers.metis.deserializers.LinkResponseDeserializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(using = LinkResponseDeserializer.class)
@Data
public class LinkResponse {
  private String loginFormURL;
  private String callbackURLPattern;
  private String sessionToken;
  private String type;

  public LinkResponse(String loginFormURL, String callbackURLPattern, String sessionToken, String type) {
    this.loginFormURL = loginFormURL;
    this.callbackURLPattern = callbackURLPattern;
    this.sessionToken = sessionToken;
    this.type = type;
  }
}