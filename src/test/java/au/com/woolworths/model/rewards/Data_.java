package au.com.woolworths.model.rewards;
import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "refreshToken",
        "refreshTokenExpiresIn",
        "accessToken",
        "accessTokenExpiresIn"
})

public class Data_ {
    @JsonProperty("refreshToken")
    private String refreshToken;
    @JsonProperty("refreshTokenExpiresIn")
    private Integer refreshTokenExpiresIn;
    @JsonProperty("accessToken")
    private String accessToken;
    @JsonProperty("accessTokenExpiresIn")
    private Integer accessTokenExpiresIn;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("refreshToken")
    public String getRefreshToken() {
        return refreshToken;
    }

    @JsonProperty("refreshToken")
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @JsonProperty("refreshTokenExpiresIn")
    public Integer getRefreshTokenExpiresIn() {
        return refreshTokenExpiresIn;
    }

    @JsonProperty("refreshTokenExpiresIn")
    public void setRefreshTokenExpiresIn(Integer refreshTokenExpiresIn) {
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
    }

    @JsonProperty("accessToken")
    public String getAccessToken() {
        return accessToken;
    }

    @JsonProperty("accessToken")
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @JsonProperty("accessTokenExpiresIn")
    public Integer getAccessTokenExpiresIn() {
        return accessTokenExpiresIn;
    }

    @JsonProperty("accessTokenExpiresIn")
    public void setAccessTokenExpiresIn(Integer accessTokenExpiresIn) {
        this.accessTokenExpiresIn = accessTokenExpiresIn;
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
