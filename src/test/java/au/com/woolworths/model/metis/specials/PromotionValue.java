package au.com.woolworths.model.metis.specials;

import au.com.woolworths.helpers.metis.deserializers.specials.PromotionValueDeserializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(using = PromotionValueDeserializer.class)
@Data
public class PromotionValue {

  private String text;
  private String dollars;
  private String cents;
  private String type;

  public PromotionValue(String text, String dollars, String cents, String type) {
    this.text = text;
    this.dollars = dollars;
    this.cents = cents;
    this.type = type;

  }

}
