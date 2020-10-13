package au.com.woolworths.helpers.metis.deserializers.specials;

import au.com.woolworths.model.metis.specials.Products;
import au.com.woolworths.model.metis.specials.PromotionValue;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class ProductsDeserializer extends StdDeserializer<Products> {

  protected ProductsDeserializer() {
    this(null);
  }

  protected ProductsDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public Products deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    JsonNode node = jp.getCodec().readTree(jp);
    ObjectMapper objectMapper = new ObjectMapper();

    String productId = node.get("data").get("productId").toString();
    String name = node.get("data").get("name").toString();
    String productImage = node.get("data").get("productImage").toString();
    double price = node.get("data").get("price").intValue();
    String unitPriceDescription = node.get("data").get("unitPriceDescription").toString();

    PromotionValue promotionValue = null;
    if (node.get("data").has("promotionValue")) {
      String promotionValueJson = node.get("data").get("promotionValue").toString();
      promotionValue = objectMapper.readValue(promotionValueJson, PromotionValue.class);
    }

    return new Products(productId, name, productImage, price, unitPriceDescription, promotionValue);

  }
}
