package au.com.woolworths.helpers.rewards.deserializers;

import au.com.woolworths.model.rewards.RewardsCard;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class RewardsCardDeserializer extends StdDeserializer<RewardsCard> {

  public RewardsCardDeserializer() {
    this(null);
  }

  public RewardsCardDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public RewardsCard deserialize(JsonParser jp, DeserializationContext ctxt)
      throws IOException {
    JsonNode node = jp.getCodec().readTree(jp);

    String cardNumber = node.get("data").get("cardNumber").asText();
    String displayCardNumber = node.get("data").get("displayCardNumber").asText();
    String displayName = node.get("data").get("displayName").asText();
    String cardType = node.get("data").get("cardType").asText();

    return new RewardsCard(cardNumber, displayCardNumber, displayName, cardType);
  }
}