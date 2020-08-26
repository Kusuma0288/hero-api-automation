package au.com.woolworths.helpers.metis.deserializers;

import au.com.woolworths.model.metis.LinkResponse;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class LinkResponseDeserializer extends StdDeserializer<LinkResponse> {

  public LinkResponseDeserializer() {
    this(null);
  }

  public LinkResponseDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public LinkResponse deserialize(JsonParser jp, DeserializationContext ctxt)
      throws IOException {
    JsonNode node = jp.getCodec().readTree(jp);

    String loginFormURL = node.get("data").get("loginFormURL").asText();
    String callbackURLPattern = node.get("data").get("callbackURLPattern").asText();
    String sessionToken = node.get("data").get("sessionToken").asText();
    String type = node.get("type").asText();

    return new LinkResponse(loginFormURL, callbackURLPattern, sessionToken, type);
  }
}