package au.com.woolworths.helpers.metis.deserializers.specials;

import au.com.woolworths.model.metis.specials.PromotionValue;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class PromotionValueDeserializer extends StdDeserializer<PromotionValue> {

  protected PromotionValueDeserializer() {
    this(null);
  }

  protected PromotionValueDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public PromotionValue deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    JsonNode node = jp.getCodec().readTree(jp);

    String text = node.get("data").get("text").toString();
    String dollars = node.get("data").get("dollars").toString();
    String cents = node.get("data").get("cents").toString();
    String type = node.get("type").toString();

    return new PromotionValue(text, dollars, cents, type);

  }
}
