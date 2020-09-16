package au.com.woolworths.model.metis.specials;

import au.com.woolworths.helpers.metis.deserializers.specials.PartnersDeserializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(using = PartnersDeserializer.class)
@Data
public class Partners {

  private Products[] products;
  private Details details;
  private Message message;

  public Partners(Products[] products, Details details, Message message) {
    this.products = products;
    this.details = details;
    this.message = message;

  }
}
