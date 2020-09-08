package au.com.woolworths.helpers.metis.deserializers;

import au.com.woolworths.model.metis.redemptionSettings.RewardsRedemptionSettings;
import au.com.woolworths.model.metis.redemptionSettings.RedemptionSettingsResponse;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class RedemptionSettingsDeserializer extends StdDeserializer<RedemptionSettingsResponse> {

    public RedemptionSettingsDeserializer() {
        this(null);
    }

    public RedemptionSettingsDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public RedemptionSettingsResponse deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        ObjectMapper objectMapper = new ObjectMapper();

        String RedemptionSettingsResponseJson = node.get("data").get("rewardsRedemptionSettings").toString();
        RewardsRedemptionSettings rewardsRedemptionSettings = objectMapper.readValue(RedemptionSettingsResponseJson, RewardsRedemptionSettings.class);

        return new RedemptionSettingsResponse(rewardsRedemptionSettings);
    }
}
