package au.com.woolworths.helpers.rewards.deserializers;

import au.com.woolworths.model.rewards.Auth;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class AuthDeserializer extends StdDeserializer<Auth> {

  public AuthDeserializer() {
    this(null);
  }

  public AuthDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public Auth deserialize(JsonParser jp, DeserializationContext ctxt)
      throws IOException {
    JsonNode node = jp.getCodec().readTree(jp);

    String refreshToken = node.get("data").get("refreshToken").asText();
    Integer refreshTokenExpiresIn = node.get("data").get("refreshTokenExpiresIn").asInt();
    String accessToken = node.get("data").get("accessToken").asText();
    Integer accessTokenExpiresIn = node.get("data").get("accessTokenExpiresIn").asInt();
    String type = node.get("type").asText();

    return new Auth(refreshToken, refreshTokenExpiresIn, accessToken, accessTokenExpiresIn, type);
  }
}