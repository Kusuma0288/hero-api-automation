package au.com.woolworths.model.rewards;
import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "visitorId"
})

public class Data___ {
    @JsonProperty("visitorId")
    private String visitorId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("visitorId")
    public String getVisitorId() {
        return visitorId;
    }

    @JsonProperty("visitorId")
    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
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
