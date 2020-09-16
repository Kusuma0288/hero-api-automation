package au.com.woolworths.helpers.metis.deserializers.specials;

import au.com.woolworths.model.metis.specials.ActionButton;
import au.com.woolworths.model.metis.specials.Message;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class MessageDeserializer extends StdDeserializer<Message> {

  public MessageDeserializer() {
    this(null);
  }

  protected MessageDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public Message deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    JsonNode node = jp.getCodec().readTree(jp);
    ObjectMapper objectMapper = new ObjectMapper();

    String title = node.get("data").get("title").toString();
    String imageUrl = node.get("data").get("imageUrl").toString();

    ActionButton actionButton = null;
    if (node.get("data").has("actionButton")) {
      String actionButtonJson = node.get("data").get("actionButton").toString();
      actionButton = objectMapper.readValue(actionButtonJson, ActionButton.class);
    }

    String type = node.get("type").toString();

    return new Message(title, imageUrl, actionButton, type);
  }

}
