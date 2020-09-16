package au.com.woolworths.helpers.metis.deserializers.specials;

import au.com.woolworths.model.metis.specials.Partners;
import au.com.woolworths.model.metis.specials.SpecialsResponse;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class SpecialsDeserializer extends StdDeserializer<SpecialsResponse> {

  protected SpecialsDeserializer() {
    this(null);
  }

  protected SpecialsDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public SpecialsResponse deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    JsonNode node = jp.getCodec().readTree(jp);
    ObjectMapper objectMapper = new ObjectMapper();

    String PartnersJson = node.get("data").get("partners").toString();
    Partners[] partners = objectMapper.readValue(PartnersJson, Partners[].class);
    String type = node.get("type").asText();

    return new SpecialsResponse(partners, type);
  }
}
