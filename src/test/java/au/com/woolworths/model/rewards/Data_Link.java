package au.com.woolworths.model.rewards;
import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "loginFormURL",
        "callbackURLPattern",
        "sessionToken"
})

public class Data_Link {
    @JsonProperty("loginFormURL")
    private String loginFormURL;
    @JsonProperty("callbackURLPattern")
    private String callbackURLPattern;
    @JsonProperty("sessionToken")
    private String sessionToken;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("loginFormURL")
    public String getLoginFormURL() {
        return loginFormURL;
    }

    @JsonProperty("loginFormURL")
    public void setLoginFormURL(String loginFormURL) {
        this.loginFormURL = loginFormURL;
    }

    @JsonProperty("callbackURLPattern")
    public String getCallbackURLPattern() {
        return callbackURLPattern;
    }

    @JsonProperty("callbackURLPattern")
    public void setCallbackURLPattern(String callbackURLPattern) {
        this.callbackURLPattern = callbackURLPattern;
    }

    @JsonProperty("sessionToken")
    public String getSessionToken() {
        return sessionToken;
    }

    @JsonProperty("sessionToken")
    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

