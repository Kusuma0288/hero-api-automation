
package au.com.woolworths.model.rewards;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "data",
    "type"
})
public class rewards_api {

    @JsonProperty("data")
    private Data data;
    @JsonProperty("type")
    private String type;
    @JsonProperty("statusCode")
    private String statusCode;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("data")
    public Data getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(Data data) {
        this.data = data;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @JsonAnySetter
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    @JsonAnySetter
    public String getStatusCode() {
        return statusCode;
    }
}
