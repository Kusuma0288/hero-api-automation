package au.com.woolworths.helpers.rewards.deserializers;

import au.com.woolworths.model.rewards.Analytics;
import au.com.woolworths.model.rewards.Auth;
import au.com.woolworths.model.rewards.LoginResponse;
import au.com.woolworths.model.rewards.RewardsCard;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class LoginResponseDeserializer extends StdDeserializer<LoginResponse> {

  public LoginResponseDeserializer() {
    this(null);
  }

  public LoginResponseDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public LoginResponse deserialize(JsonParser jp, DeserializationContext ctxt)
      throws IOException {
    JsonNode node = jp.getCodec().readTree(jp);
    ObjectMapper objectMapper = new ObjectMapper();

    String authJson = node.get("data").get("auth").toString();
    Auth auth = objectMapper.readValue(authJson, Auth.class);

    String rewardsCardJson = node.get("data").get("rewardsCard").toString();
    RewardsCard rewardsCard = new ObjectMapper().readValue(rewardsCardJson, RewardsCard.class);

    String analyticsJson = node.get("data").get("analytics").toString();
    Analytics rewardsAnalytics = new ObjectMapper().readValue(analyticsJson, Analytics.class);

    return new LoginResponse(auth, rewardsCard, rewardsAnalytics);
  }
}