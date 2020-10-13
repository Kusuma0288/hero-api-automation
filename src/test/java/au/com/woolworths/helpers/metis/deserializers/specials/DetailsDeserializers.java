package au.com.woolworths.helpers.metis.deserializers.specials;

import au.com.woolworths.model.metis.specials.Details;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;


public class DetailsDeserializers extends StdDeserializer<Details> {

  public DetailsDeserializers() {
    this(null);
  }

  protected DetailsDeserializers(Class<?> vc) {
    super(vc);
  }

  @Override
  public Details deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    JsonNode node = jp.getCodec().readTree(jp);

    String name = node.get("data").get("name").toString();
    String partnerId = node.get("data").get("partnerId").toString();
    String iconUrl = node.get("data").get("iconUrl").toString();
    String subtitle = node.get("data").get("subtitle").toString();
    String type = node.get("type").toString();

    return new Details(name, partnerId, iconUrl, subtitle, type);

  }
}
