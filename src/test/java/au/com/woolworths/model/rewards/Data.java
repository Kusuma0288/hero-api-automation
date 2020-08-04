package au.com.woolworths.model.rewards;
import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "auth",
        "rewardsCard",
        "analytics"
})

public class Data {
    @JsonProperty("auth")
    private Auth auth;
    @JsonProperty("rewardsCard")
    private RewardsCard rewardsCard;
    @JsonProperty("analytics")
    private RewardsAnalytics analytics;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("auth")
    public Auth getAuth() {
        return auth;
    }

    @JsonProperty("auth")
    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    @JsonProperty("rewardsCard")
    public RewardsCard getRewardsCard() {
        return rewardsCard;
    }

    @JsonProperty("rewardsCard")
    public void setRewardsCard(RewardsCard rewardsCard) {
        this.rewardsCard = rewardsCard;
    }

    @JsonProperty("analytics")
    public RewardsAnalytics getAnalytics() {
        return analytics;
    }

    @JsonProperty("analytics")
    public void setAnalytics(RewardsAnalytics analytics) {
        this.analytics = analytics;
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
