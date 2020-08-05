package au.com.woolworths.model.rewards;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "authCode",
        "sessionToken",
        "deviceId"
})

public class MetisLoginRequest {
    private String authCode;
    private String deviceId;
    private String sessionToken;

    @Override
    public String toString() {
        return "MetisLoginRequest {" +
                " authCode=" + authCode + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", sessionToken='" + sessionToken + '\'' +
                '}';
    }

    @JsonProperty("authCode")
    public String getDevice_authCode() {
        return authCode;
    }

    @JsonProperty("authCode")
    public void setauthCode(String authCode) {
        this.authCode = authCode;
    }

    @JsonProperty("deviceId")
    public String getdeviceId() {
        return deviceId;
    }

    @JsonProperty("deviceId")
    public void setdeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @JsonProperty("sessionToken")
    public String getsessionToken() {
        return sessionToken;
    }

    @JsonProperty("sessionToken")
    public void setsessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }
}
