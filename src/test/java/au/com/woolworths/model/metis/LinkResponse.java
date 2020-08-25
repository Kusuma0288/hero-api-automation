package au.com.woolworths.model.metis;

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

  public LinkResponse(String loginFormURL, String callbackURLPattern, String sessionToken) {
    this.loginFormURL = loginFormURL;
    this.callbackURLPattern = callbackURLPattern;
    this.sessionToken = sessionToken;
  }
}