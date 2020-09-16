package au.com.woolworths.helpers.metis.deserializers.specials;

import au.com.woolworths.model.metis.specials.ActionButton;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class ActionButtonDeserializer extends StdDeserializer<ActionButton> {

  protected ActionButtonDeserializer() {
    this(null);
  }

  protected ActionButtonDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public ActionButton deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    JsonNode node = jp.getCodec().readTree(jp);

    String title = node.get("data").get("title").toString();
    String url = node.get("data").get("url").toString();
    String type = node.get("type").toString();

    return new ActionButton(title, url, type);

  }
}
