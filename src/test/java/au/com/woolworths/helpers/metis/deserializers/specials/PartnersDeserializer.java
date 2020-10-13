package au.com.woolworths.helpers.metis.deserializers.specials;

import au.com.woolworths.model.metis.specials.Details;
import au.com.woolworths.model.metis.specials.Message;
import au.com.woolworths.model.metis.specials.Partners;
import au.com.woolworths.model.metis.specials.Products;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class PartnersDeserializer extends StdDeserializer<Partners> {

  protected PartnersDeserializer() {
    this(null);
  }

  protected PartnersDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public Partners deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    JsonNode node = jp.getCodec().readTree(jp);
    ObjectMapper objectMapper = new ObjectMapper();

    String productsJson = node.get("data").get("products").toString();
    Products[] products = objectMapper.readValue(productsJson, Products[].class);

    String detailsJson = node.get("data").get("details").toString();
    Details details = objectMapper.readValue(detailsJson, Details.class);

    Message message = null;
    if (node.get("data").has("message")) {
      String messageJson = node.get("data").get("message").toString();
      message = objectMapper.readValue(messageJson, Message.class);
    }

    return new Partners(products, details, message);
  }
}
