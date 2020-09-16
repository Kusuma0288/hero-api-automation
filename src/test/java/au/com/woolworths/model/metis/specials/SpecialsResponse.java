package au.com.woolworths.model.metis.specials;

import au.com.woolworths.helpers.metis.deserializers.specials.SpecialsDeserializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(using = SpecialsDeserializer.class)
@Data
public class SpecialsResponse {

  private Partners[] partners;
  private String type;
  private String statusCode;

  public SpecialsResponse(Partners[] partners, String type) {
    this.partners = partners;
    this.type = type;
  }
}
